package org.openreading.readingisgood.business;

import org.apache.logging.log4j.Logger;
import org.openreading.readingisgood.model.Book;
import org.openreading.readingisgood.model.BookStockRequest;
import org.openreading.readingisgood.model.Item;
import org.openreading.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/** @author Muhammet Sakarya created at 8/14/2021 */
@Component
public class BookBusinessImpl implements BookBusiness {
  private static final Logger log =
      org.apache.logging.log4j.LogManager.getLogger(BookBusinessImpl.class);
  private BookRepository bookRepository;
  private ReactiveRedisTemplate<String, Integer> bookCache;

  @Autowired
  public BookBusinessImpl(
      BookRepository bookRepository, ReactiveRedisTemplate<String, Integer> bookCache) {
    this.bookRepository = bookRepository;
    this.bookCache = bookCache;
  }

  @Override
  public Mono<Book> updateStock(BookStockRequest bookStockRequest) {
    return bookRepository
        .findById(bookStockRequest.getId())
        .map(
            book -> {
              book.setStock(bookStockRequest.getStock());
              log.info(book);
              return book;
            })
        .doOnSuccess(
            book -> {
              bookRepository.save(book).subscribe();
            });
  }

  /**
   * To achive stock consistency cache mechanism used. This method first checks if the key exists in
   * the cache, if it does it tries to decrease the stock in the cache and then updates the DB. if
   * the key does not exist then add it into cache and pull from DB, try to decrease stock and
   * finally update both cache and DB.
   *
   * @param item reserve book and update in item model.
   * @param quantity decrease book stock value
   * @return
   */
  @Override
  public Mono<Item> reserveBook(Item item, @NonNull Integer quantity) {
    if (quantity < 1) {
      throw new QuantityNotValidException();
    }
    String bookId = item.getBook().getId();
    return bookCache
        .opsForValue()
        .setIfAbsent(bookId, -1)
        .flatMap(
            newRecord -> {
              final Mono<Book> processStock;
              if (newRecord) {
                log.info("persisting book with id ".concat(bookId));
                processStock =
                    bookRepository
                        .findById(bookId)
                        .flatMap(book -> updateStock(quantity, bookId))
                        .flatMap(book ->  this.updateCacheAndDb(book,true));
              } else {
                processStock =
                    getStock(bookId)
                        .flatMap(
                            updated -> {
                              if (!updated) return getStock(bookId);
                              return Mono.just(true);
                            })
                        .flatMap(
                            updated2 -> {
                              if (!updated2) {
                                throw new QuantityNotFetchedException("");
                              }
                              return bookCache
                                  .opsForValue()
                                  .get(bookId)
                                  .flatMap(stock -> this.updateStock(stock, bookId))
                                  .flatMap(book -> this.updateCacheAndDb(book,false));
                            });
              }
              return processStock.map(
                  book -> {
                    item.setBook(book);
                    return item;
                  });
            });
  }

  private Mono<Boolean> getStock(String bookId) {
    return bookCache
        .opsForValue()
        .get(bookId)
        .flatMap(
            stock -> {
              if (stock < 0) {
                return Mono.delay(Duration.ofMillis(100)).then(Mono.just(false));
              }
              return Mono.just(true);
            });
  }

  private Mono<Book> updateStock(Integer quantity, String bookId) {
    return bookRepository
        .findById(bookId)
        .map(
            book -> {
              if (book == null) {
                throw new BookNotFoundException(
                    "The book with id: ".concat(bookId).concat(" could not found on db"));
              }

              if (book.getStock() == null || book.getStock() < quantity) {
                throw new NotEnoughStockException(
                    "Stock for ".concat(book.getId()).concat("was not enough"));
              }
              book.setStock(book.getStock() - quantity);
              return book;
            });
  }

  private Mono<Book> updateCacheAndDb(Book book, Boolean initial ) {
      if (initial){
          return bookCache.opsForValue().set(book.getId(),book.getStock()).map(success -> {
              if(!success){
                  throw new CacheUpdateException("");
              }
              return book;
          });
      }
    return bookCache
        .opsForValue()
        .decrement(book.getId())
        .map(
            val -> {
              book.setStock(val.intValue());
              return book;
            })
        .flatMap(bookRepository::save);
  }
}

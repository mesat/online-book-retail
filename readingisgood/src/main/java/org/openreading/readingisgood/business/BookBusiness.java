package org.openreading.readingisgood.business;

import org.openreading.readingisgood.model.Book;
import org.openreading.readingisgood.model.BookStockRequest;
import org.openreading.readingisgood.model.Item;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public interface BookBusiness {

    Mono<Book> updateStock(BookStockRequest bookStockRequest);

    Mono<Item> reserveBook(Item item, @NonNull  Integer quantity);
}

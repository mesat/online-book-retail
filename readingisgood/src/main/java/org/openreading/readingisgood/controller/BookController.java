package org.openreading.readingisgood.controller;

import org.apache.logging.log4j.Logger;
import org.openreading.readingisgood.business.BookBusiness;
import org.openreading.readingisgood.model.Book;
import org.openreading.readingisgood.model.BookStockRequest;
import org.openreading.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@RestController
public class BookController {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(BookController.class);
    private BookRepository bookRepository;
    private BookBusiness bookBusiness;

    @Autowired
    public BookController(BookRepository bookRepository, BookBusiness bookBusiness) {
        this.bookRepository = bookRepository;
        this.bookBusiness = bookBusiness;
    }

    @GetMapping("/books")
    public Flux<Book> getBooks(){
        return bookRepository.findAll();

    }

    @PostMapping("/book")
    public Mono<Book> saveBook(@RequestBody @NonNull Book book){

        return bookRepository.save(book).log();
    }
    @PostMapping("/book/stock")
    public Mono<Book> updateStock(@RequestBody BookStockRequest bookStockRequest){
        return bookBusiness.updateStock(bookStockRequest);

    }

}

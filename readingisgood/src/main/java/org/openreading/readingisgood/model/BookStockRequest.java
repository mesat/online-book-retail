package org.openreading.readingisgood.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
@Document("book")
public class BookStockRequest {
    private String id;
    private Integer stock;

    public BookStockRequest(){

    }
    public BookStockRequest(String id, Integer stock) {
        this.id = id;
        this.stock = stock;
    }

    public static BookStockRequestBuilder builder() {
        return new BookStockRequestBuilder();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Book getBook(){
        Book book = new Book();
        book.setId(getId());
        book.setStock(stock);
        return book;
    }

    public static class BookStockRequestBuilder {
        private String id;
        private Integer stock;

        BookStockRequestBuilder() {
        }

        public BookStockRequestBuilder id(String id) {
            this.id = id;
            return this;
        }

        public BookStockRequestBuilder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public BookStockRequest build() {
            return new BookStockRequest(id, stock);
        }

        public String toString() {
            return "BookStockRequest.BookStockRequestBuilder(id=" + this.id + ", stock=" + this.stock + ")";
        }
    }
}

package org.openreading.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@Document
public class Book extends Base {


    private String title;
    private String author;
    private Integer year;
    private String publisher;
    private String isbn;
    private Integer stock;
    private Double cost;
    private Double price;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String toString() {
        return "Book(title=" + this.getTitle() + ", author=" + this.getAuthor() + ", year=" + this.getYear() + ", publisher=" + this.getPublisher() + ", isbn=" + this.getIsbn() + ", stock=" + this.getStock() + ", cost=" + this.getCost() + ", price=" + this.getPrice() + ")";
    }
}

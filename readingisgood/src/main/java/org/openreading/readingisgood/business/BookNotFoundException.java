package org.openreading.readingisgood.business;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String concat) {
        super(concat);
    }
}

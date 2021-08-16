package org.openreading.readingisgood.model;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public enum OrderStatus {
    BOOKING("BOOKING"),
    ORDERED("ORDERED"),
    PREPARING("PREPARING"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED"),
    RETURN_REQUESTED("RETURN_REQUESTED"),
    RETURN_ACCEPTED("RETURN_ACCEPTED")
    //i.e.

    ;

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}

package org.openreading.readingisgood.model;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */

public class Cart extends Base{
    public Cart() {
        // Sub document ids' are not generated automatically
        super.setId(new ObjectId().toString());
    }


    private List<Item> items;
    private Double totalPrice;

    public Double getTotalPrice() {
        this.totalPrice = items.stream().filter(item ->  Objects.nonNull(item.getBook().getPrice())).mapToDouble(value -> value.getBook().getPrice()).sum();
        return totalPrice;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

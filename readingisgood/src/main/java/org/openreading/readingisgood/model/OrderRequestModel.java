package org.openreading.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammet Sakarya
 * created at 8/16/2021
 */
public class OrderRequestModel {
    @JsonIgnore
    private Order order;
    private List<String> itemsInChart;

    public OrderRequestModel(){
        this.setOrder(new Order());
    }

    @JsonProperty("itemsInChart")
    public List<String> getItemsInChart() {
        return this.order.getCart().getItems().stream().map(Item::getBook).map(Base::getId).toList();
    }

    public void setItemsInChart(List<String> itemsInChart) {
        order.setCart(new Cart());
        order.getCart().setItems(new ArrayList<>());

        itemsInChart.forEach(bookId -> {
            Item item = new Item();
            item.setBook(new Book());
            item.getBook().setId(bookId);
            this.order.getCart().getItems().add(item);
        });
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

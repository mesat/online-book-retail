package org.openreading.readingisgood.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public class Item extends Base {

    public Item() {
        // Sub document ids' are not generated automatically
        super.setId(new ObjectId().toString());
    }
    private Book book;
    //The customer may want to return a single item from the cart.
    private OrderStatus orderStatus;


  public Book getBook() {
        return book;
    }

  /** If the customer wants to buy the same book more than 1, we have to add it to the list repetitively.
   *  @param book in the {@link Cart}
   *  */
  public void setBook(Book book) {
        this.book = book;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

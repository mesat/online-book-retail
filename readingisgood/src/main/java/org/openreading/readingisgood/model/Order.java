package org.openreading.readingisgood.model;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/** @author Muhammet Sakarya created at 8/15/2021 */
public class Order extends Base {
  public Order() {
    // Sub document ids' are not generated automatically
    super.setId(new ObjectId().toString());
  }

  /** Start date of the order */
  private Date startDate;
  /** Start date of the order in long format */
  private Long startDateEpoch;

  /** Order's current status. History of the orders kept in the {@link OrderTransaction } */
  private OrderStatus status;

  /** Order's all transactions */
  private List<OrderTransaction> orderTransactions;

  private Cart cart;

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
    this.startDateEpoch = startDate.toInstant().toEpochMilli();
  }

  public List<OrderTransaction> getOrderTransactions() {
    return orderTransactions;
  }

  public void setOrderTransactions(List<OrderTransaction> orderTransactions) {
    this.orderTransactions = orderTransactions;
  }

  public Long getStartDateEpoch() {
    return startDateEpoch;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}

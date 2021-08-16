package org.openreading.readingisgood.business;

import org.openreading.readingisgood.model.Order;
import org.openreading.readingisgood.model.OrderStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public interface CustomerBusiness {

    Mono<Order> saveOrder();
    Mono<Order> updateOrderStatus (OrderStatus orderStatus);

    Mono<Order> saveOrder(String customerId, Order order);
    Flux<Order> findOrdersBetweenStartDate(Date from, Date to);
}

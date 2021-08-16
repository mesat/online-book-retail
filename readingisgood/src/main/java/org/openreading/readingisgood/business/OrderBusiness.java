package org.openreading.readingisgood.business;

import org.openreading.readingisgood.model.Order;
import reactor.core.publisher.Mono;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
public interface OrderBusiness {
    public Mono<Order> saveOrder(String customerId, Order order);
}

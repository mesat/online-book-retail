package org.openreading.readingisgood.business;

import org.openreading.readingisgood.model.Order;
import org.openreading.readingisgood.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

/**
 * @author Muhammet Sakarya
 * created at 8/15/2021
 */
@Component
public class OrderBusinessImpl implements OrderBusiness {
    private BookBusiness bookBusiness;
    private CustomerBusiness customerBusiness;

    @Autowired
    public OrderBusinessImpl(BookBusiness bookBusiness, CustomerBusiness customerBusiness) {
        this.bookBusiness = bookBusiness;
        this.customerBusiness = customerBusiness;
    }

    @Override
    public Mono<Order> saveOrder(String customerId, Order order) {
        order.setStatus(OrderStatus.BOOKING);
        order.setStartDate(Calendar.getInstance().getTime());
        return Flux.fromIterable(order.getCart().getItems()).flatMap(item -> bookBusiness.reserveBook(item,1))
        .then(customerBusiness.saveOrder(customerId,order));
    }
}

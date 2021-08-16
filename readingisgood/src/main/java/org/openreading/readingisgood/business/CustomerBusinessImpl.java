package org.openreading.readingisgood.business;

import org.openreading.readingisgood.model.Order;
import org.openreading.readingisgood.model.OrderStatus;
import org.openreading.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/** @author Muhammet Sakarya created at 8/15/2021 */
@Component
public class CustomerBusinessImpl implements CustomerBusiness {

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerBusinessImpl(CustomerRepository customerRepository, BookBusiness bookBusiness) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Mono<Order> saveOrder() {
    return null;
  }

  @Override
  public Mono<Order> updateOrderStatus(OrderStatus orderStatus) {
    return null;
  }

  @Override
  public Mono<Order> saveOrder(String customerId, Order order) {

    return customerRepository
        .findById(customerId)
        .flatMap(
            customer -> {
              customer.getOrders().add(order);
              return customerRepository
                  .save(customer)
                  .map(
                      customer1 ->
                          customer1.getOrders().stream()
                              .filter(order1 -> order1.getId().equals(order.getId()))
                              .findFirst()
                              .get());
            });
  }

  @Override
  public Flux<Order> findOrdersBetweenStartDate(Date from, Date to) {
    return customerRepository.findOrdersBetweenStartDate(
        from.toInstant().toEpochMilli(), to.toInstant().toEpochMilli());
  }
}

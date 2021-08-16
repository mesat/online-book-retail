package org.openreading.readingisgood.repository;

import org.openreading.readingisgood.model.Customer;
import org.openreading.readingisgood.model.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {

    @Query("{ 'orders.startDateEpoch' : { $gte: ?0, $lte: ?1 } }")
    Flux<Order> findOrdersBetweenStartDate(long from, long to);
}


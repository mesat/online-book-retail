package org.openreading.readingisgood.controller;

import org.apache.logging.log4j.Logger;
import org.openreading.readingisgood.business.CustomerBusiness;
import org.openreading.readingisgood.business.OrderBusiness;
import org.openreading.readingisgood.model.Customer;
import org.openreading.readingisgood.model.Order;
import org.openreading.readingisgood.model.OrderRequestModel;
import org.openreading.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@RestController
@RequestMapping("customer")
public class CustomerController {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(CustomerController.class);
    private CustomerRepository customerRepository;
    private CustomerBusiness customerBusiness;
    private OrderBusiness orderBusiness;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerBusiness customerBusiness, OrderBusiness orderBusiness) {
        this.customerRepository = customerRepository;
        this.customerBusiness = customerBusiness;
        this.orderBusiness = orderBusiness;
    }


    @GetMapping("/all")
    public Flux<Customer> getCustomers(){
        return customerRepository.findAll();

    }

    @PostMapping
    public Mono<Customer> saveCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Flux<Order> getOrdersofCustomer(@PathVariable String id){
        return customerRepository.findById(id).flatMapMany(CustomerController::apply);
    }

    private static Flux<Order> apply(Customer customer) {
        return  Flux.fromIterable(customer.getOrders());
    }
    @PostMapping("/{customerId}/order")
    public Mono<Order> newOrder(@PathVariable String customerId, @RequestBody OrderRequestModel order){
        return orderBusiness.saveOrder(customerId,order.getOrder());

    }




}

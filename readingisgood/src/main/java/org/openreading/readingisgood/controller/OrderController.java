package org.openreading.readingisgood.controller;

import org.apache.logging.log4j.Logger;
import org.openreading.readingisgood.business.CustomerBusiness;
import org.openreading.readingisgood.model.Order;
import org.openreading.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Date;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 * Provides common order API. Customer based Order operations are kept on {@link CustomerController}
 */

@RestController
@RequestMapping("orders")
public class OrderController {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(OrderController.class);
    private CustomerBusiness customerBusiness;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderController(CustomerBusiness customerBusiness, CustomerRepository customerRepository) {
        this.customerBusiness = customerBusiness;
        this.customerRepository = customerRepository;
    }


    @GetMapping
    public Flux<Order> getOrders(@RequestParam("from")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fromDate, @RequestParam("to")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")Date toDate){

        return customerBusiness.findOrdersBetweenStartDate(fromDate,toDate);

    }




}

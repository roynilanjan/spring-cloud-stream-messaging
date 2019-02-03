package com.nilanjan.services.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.customer.model.Customer;
import com.nilanjan.services.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Nilanjan Roy
 */
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CustomerRepository repository;

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return repository.add(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return repository.update(customer);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public Customer withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount) throws JsonProcessingException {
        Customer customer = repository.findById(id);
        LOGGER.info("Customer found: {}", mapper.writeValueAsString(customer));
        customer.setBalance(customer.getBalance() - amount);
        LOGGER.info("Current balance: {}", mapper.writeValueAsString(Collections.singletonMap("balance", customer.getBalance())));
        return repository.update(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Customer> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return repository.findByCustomer(customerId);
    }

    @PostMapping("/ids")
    public List<Customer> find(@RequestBody List<Long> ids) {
        return repository.find(ids);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }
}

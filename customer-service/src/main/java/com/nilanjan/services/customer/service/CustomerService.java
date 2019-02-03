package com.nilanjan.services.customer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.customer.client.InventoryClient;
import com.nilanjan.services.customer.messaging.OrderSender;
import com.nilanjan.services.customer.model.Customer;
import com.nilanjan.services.customer.model.Inventory;
import com.nilanjan.services.customer.repository.CustomerRepository;
import com.nilanjan.services.messaging.Order;
import com.nilanjan.services.messaging.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Nilanjan Roy
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InventoryClient inventoryClient;
    @Autowired
    OrderSender orderSender;

    public void process(final Order order) throws JsonProcessingException {
        LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
        List<Customer> customers =  customerRepository.findByCustomer(order.getCustomerId());
        Customer account = customers.get(0);
        LOGGER.info("Customer found: {}", mapper.writeValueAsString(account));
        List<Inventory> inventories = inventoryClient.findByIds(order.getProductIds());
        LOGGER.info("Products found: {}", mapper.writeValueAsString(inventories));
        inventories.forEach(p -> order.setPrice(order.getPrice() + p.getPrice()));
        if (order.getPrice() <= account.getBalance()) {
            order.setStatus(OrderStatus.APPROVED);
            account.setBalance(account.getBalance() - order.getPrice());
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        orderSender.send(order);
        LOGGER.info("Order response sent: {}", mapper.writeValueAsString(order));
    }

}

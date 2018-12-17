package com.nilanjan.services.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.account.client.ProductClient;
import com.nilanjan.services.account.messaging.OrderSender;
import com.nilanjan.services.account.model.Account;
import com.nilanjan.services.account.model.Product;
import com.nilanjan.services.account.repository.AccountRepository;
import com.nilanjan.services.messaging.Order;
import com.nilanjan.services.messaging.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nilanjan Roy
 */
@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductClient productClient;
    @Autowired
    OrderSender orderSender;

    public void process(final Order order) throws JsonProcessingException {
        LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
        List<Account> accounts =  accountRepository.findByCustomer(order.getCustomerId());
        Account account = accounts.get(0);
        LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
        List<Product> products = productClient.findByIds(order.getProductIds());
        LOGGER.info("Products found: {}", mapper.writeValueAsString(products));
        products.forEach(p -> order.setPrice(order.getPrice() + p.getPrice()));
        if (order.getPrice() <= account.getBalance()) {
            order.setStatus(OrderStatus.ACCEPTED);
            account.setBalance(account.getBalance() - order.getPrice());
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        orderSender.send(order);
        LOGGER.info("Order response sent: {}", mapper.writeValueAsString(order));
    }

}


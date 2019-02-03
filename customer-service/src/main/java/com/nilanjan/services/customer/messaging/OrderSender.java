package com.nilanjan.services.customer.messaging;

import com.nilanjan.services.messaging.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author Nilanjan Roy
 */
@Service
public class OrderSender {
    @Autowired
    private Source source;

    public boolean send(Order order) {
        return this.source.output().send(MessageBuilder.withPayload(order).build());
    }


}

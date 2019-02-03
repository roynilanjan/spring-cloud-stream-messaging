package com.nilanjan.services.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.customer.model.Customer;
import com.nilanjan.services.customer.repository.CustomerRepository;
import com.nilanjan.services.customer.service.CustomerService;
import com.nilanjan.services.messaging.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @author Nilanjan Roy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding(Processor.class)
public class CustomerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApplication.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CustomerService customerService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomerApplication.class).web(true).run(args);
    }

    @StreamListener(Processor.INPUT)
    public void receiveOrder(Order order) throws JsonProcessingException {
        LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
        customerService.process(order);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setAfterMessagePrefix("REQ:");
        return loggingFilter;
    }

    @Bean
    CustomerRepository repository() {
        CustomerRepository repository = new CustomerRepository();
        repository.add(new Customer("1234567890", 50000, 1L));
        repository.add(new Customer("1234567891", 50000, 1L));
        repository.add(new Customer("1234567892", 0, 1L));
        repository.add(new Customer("1234567893", 50000, 2L));
        repository.add(new Customer("1234567894", 0, 2L));
        repository.add(new Customer("1234567895", 50000, 2L));
        repository.add(new Customer("1234567896", 0, 3L));
        repository.add(new Customer("1234567897", 50000, 3L));
        repository.add(new Customer("1234567898", 50000, 3L));
        return repository;
    }

    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

}

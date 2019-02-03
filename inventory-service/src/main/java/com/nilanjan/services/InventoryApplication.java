package com.nilanjan.services;

import com.nilanjan.services.model.Inventory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import com.nilanjan.services.repository.InventoryRepository;

/**
 * @author Nilanjan Roy
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(InventoryApplication.class).web(true).run(args);
    }

    @Bean
    InventoryRepository repository() {
        InventoryRepository repository = new InventoryRepository();
        repository.add(new Inventory(101L,"Sample-1",2500));
        repository.add(new Inventory(102L,"Sample-2",2500));
        repository.add(new Inventory(103L,"Sample-3",2500));
        repository.add(new Inventory(104L,"Sample-4",2500));
        repository.add(new Inventory(105L,"Sample-5",2500));
        repository.add(new Inventory(106L,"Sample-6",2500));
        repository.add(new Inventory(107L,"Sample-7",2500));
        repository.add(new Inventory(108L,"Sample-8",2500));
        repository.add(new Inventory(109L,"Sample-9",2500));
        repository.add(new Inventory(110L,"Sample-10",2500));
        return repository;
    }

}

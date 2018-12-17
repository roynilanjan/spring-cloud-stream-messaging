package com.nilanjan.services.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.order.model.Product;
import com.nilanjan.services.order.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Nilanjan Roy
 */
@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ProductRepository repository;

    @PostMapping
    public Product add(@RequestBody Product product) {
        return repository.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return repository.update(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping("/ids")
    public List<Product> find(@RequestBody List<Long> ids) throws JsonProcessingException {
        List<Product> products = repository.find(ids);
        LOGGER.info("Products found: {}", mapper.writeValueAsString(Collections.singletonMap("count", products.size())));
        return products;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

}

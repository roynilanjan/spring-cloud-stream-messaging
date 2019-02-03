package com.nilanjan.services.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilanjan.services.model.Inventory;
import com.nilanjan.services.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Nilanjan Roy
 */
public class InventoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    InventoryRepository repository;

    @PostMapping
    public Inventory add(@RequestBody Inventory inventory) {
        return repository.add(inventory);
    }

    @PutMapping
    public Inventory update(@RequestBody Inventory product) {
        return repository.update(product);
    }

    @GetMapping("/{id}")
    public Inventory findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping("/ids")
    public List<Inventory> find(@RequestBody List<Long> ids) throws JsonProcessingException {
        List<Inventory> products = repository.find(ids);
        LOGGER.info("Products found: {}", mapper.writeValueAsString(Collections.singletonMap("count", products.size())));
        return products;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

}

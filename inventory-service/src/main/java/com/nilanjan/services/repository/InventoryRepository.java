package com.nilanjan.services.repository;

import com.nilanjan.services.model.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nilanjan Roy
 */
public class InventoryRepository {

    private List<Inventory> inventories = new ArrayList<>();

    public Inventory add(Inventory product) {
        product.setId((long) (inventories.size()+1));
        inventories.add(product);
        return product;
    }

    public Inventory update(Inventory product) {
        inventories.set(product.getId().intValue() - 1, product);
        return product;
    }

    public Inventory findById(Long id) {
        Optional<Inventory> product = inventories.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (product.isPresent())
            return product.get();
        else
            return null;
    }

    public void delete(Long id) {
        inventories.remove(id.intValue());
    }

    public List<Inventory> find(List<Long> ids) {
        return inventories.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }
}

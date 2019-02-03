package com.nilanjan.services.customer.client;

import com.nilanjan.services.customer.model.Inventory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Nilanjan Roy
 */
@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/ids")
    List<Inventory> findByIds(@RequestBody List<Long> ids);

}

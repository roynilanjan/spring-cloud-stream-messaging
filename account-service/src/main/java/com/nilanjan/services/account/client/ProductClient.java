package com.nilanjan.services.account.client;

import com.nilanjan.services.account.model.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Nilanjan Roy
 */

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/ids")
    List<Product> findByIds(@RequestBody List<Long> ids);

}

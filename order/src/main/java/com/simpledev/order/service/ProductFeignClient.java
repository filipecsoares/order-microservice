package com.simpledev.order.service;

import com.simpledev.order.protocols.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("product")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}

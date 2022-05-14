package com.simpledev.product.codec;

import com.simpledev.product.model.Product;
import com.simpledev.product.protocols.ProductResponse;
import com.simpledev.product.protocols.ProductsResponse;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Codec {
    public static ProductResponse toResponse(Product product) {
        var response = new ProductResponse();
        BeanUtils.copyProperties(product, response);
        return response;
    }

    public static ProductsResponse toResponse(List<Product> products) {
        return new ProductsResponse(products.stream().map(Codec::toResponse).collect(Collectors.toList()));
    }
}

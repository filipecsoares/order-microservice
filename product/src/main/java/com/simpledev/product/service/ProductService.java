package com.simpledev.product.service;

import com.simpledev.product.model.Product;

import java.util.List;

public interface ProductService {
    public Product save(Product product);

    public Product update(Long id, Product product);

    public List<Product> findAll();

    public Product findById(Long id);

    public Product findByName(String name);
}

package com.simpledev.product.service;

import com.simpledev.product.model.Product;
import com.simpledev.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        var foundProduct = productRepository.findById(id);
        if(foundProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not exists!");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }
}

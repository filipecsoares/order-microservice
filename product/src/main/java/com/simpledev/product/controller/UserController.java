package com.simpledev.product.controller;

import com.simpledev.product.codec.Codec;
import com.simpledev.product.model.Product;
import com.simpledev.product.protocols.ProductResponse;
import com.simpledev.product.protocols.ProductsResponse;
import com.simpledev.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class UserController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody Product product) {
        return new ResponseEntity<>(Codec.toResponse(productService.save(product)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(Codec.toResponse(productService.update(id, product)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> findAll() {
        return new ResponseEntity<>(Codec.toResponse(productService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> findByEmail(@PathVariable String name) {
        return new ResponseEntity<>(Codec.toResponse(productService.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findByEmail(@PathVariable Long id) {
        return new ResponseEntity<>(Codec.toResponse(productService.findById(id)), HttpStatus.OK);
    }
}

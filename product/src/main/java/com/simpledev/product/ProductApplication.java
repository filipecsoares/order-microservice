package com.simpledev.product;

import com.simpledev.product.model.Product;
import com.simpledev.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class ProductApplication {

    private final ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @PostConstruct
    public void populateProducts() {
        var products = Arrays.asList(
                Product.builder().name("Rubik Cube").price(BigDecimal.valueOf(20.99)).build(),
                Product.builder().name("The Lord of the Ring I").description("A great book").price(BigDecimal.valueOf(59.99)).build(),
                Product.builder().name("The Lord of the Ring II").description("A great book").price(BigDecimal.valueOf(69.99)).build(),
                Product.builder().name("Guitar").description("Gibson Les Paul").price(BigDecimal.valueOf(3999.99)).build()
        );
        products.stream().map(productRepository::save);
    }
}

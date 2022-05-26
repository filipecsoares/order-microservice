package com.simpledev.order.controller;

import com.simpledev.order.codec.Codec;
import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;
import com.simpledev.order.protocols.OrderResponse;
import com.simpledev.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest order) {
        return new ResponseEntity<>(Codec.toResponse(orderService.save(order)), HttpStatus.CREATED);
    }
}

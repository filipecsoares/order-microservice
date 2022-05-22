package com.simpledev.order.service;

import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Order;
import com.simpledev.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.OPENED);
        return orderRepository.save(order);
    }
}

package com.simpledev.order.service;

import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Item;
import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;
import com.simpledev.order.protocols.ProductRequest;
import com.simpledev.order.repository.ItemRepository;
import com.simpledev.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Order save(OrderRequest orderRequest) {
        if(orderRequest == null || orderRequest.getUserId() == null) {
            throw new IllegalArgumentException("Missing data on Order.");
        }
        var total = orderRequest.getProducts().stream().map(ProductRequest::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        var order = Order.builder().createdAt(LocalDateTime.now()).userId(orderRequest.getUserId()).total(total).status(OrderStatus.PENDING).build();
        var savedOrder = orderRepository.save(order);
        orderRequest.getProducts().forEach(p -> {
            var item = Item.builder().quantity(p.getQuantity()).productId(p.getId()).price(p.getPrice()).order(savedOrder).build();
            itemRepository.save(item);
        });
        return order;
    }
}

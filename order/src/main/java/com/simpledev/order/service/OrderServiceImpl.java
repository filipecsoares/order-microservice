package com.simpledev.order.service;

import com.simpledev.order.codec.Codec;
import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Item;
import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;
import com.simpledev.order.protocols.OrderResponse;
import com.simpledev.order.protocols.ProductRequest;
import com.simpledev.order.repository.ItemRepository;
import com.simpledev.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    private final ProductFeignClient productFeignClient;

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

    @Override
    public OrderResponse getById(Long orderId) {
        var order = orderRepository.getById(orderId);
        var items = itemRepository.findByOrderId(orderId);
        var products = items.stream().map(item -> productFeignClient.getProductById(item.getProductId())).collect(Collectors.toList());
        var orderResponse = Codec.toResponse(order);
        orderResponse.setProducts(products);
        return orderResponse;
    }
}

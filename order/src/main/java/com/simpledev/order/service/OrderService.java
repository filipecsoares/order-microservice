package com.simpledev.order.service;

import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;
import com.simpledev.order.protocols.OrderResponse;

public interface OrderService {
    public Order save(OrderRequest order);

    public OrderResponse getById(Long orderId);
}

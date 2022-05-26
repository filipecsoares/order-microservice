package com.simpledev.order.service;

import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;

public interface OrderService {
    public Order save(OrderRequest order);
}

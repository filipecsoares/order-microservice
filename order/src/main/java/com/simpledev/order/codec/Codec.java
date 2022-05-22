package com.simpledev.order.codec;

import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderResponse;
import org.springframework.beans.BeanUtils;

public class Codec {
    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);
        return response;
    }
}

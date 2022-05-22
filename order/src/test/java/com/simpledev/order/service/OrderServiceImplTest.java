package com.simpledev.order.service;

import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Item;
import com.simpledev.order.model.Order;
import com.simpledev.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    private Order mockOrder;

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void shouldSaveAnValidOrder() {
        var order = Order.builder().build();
        Item item1 = Item.builder().productId(1L).quantity(1).order(order).build();

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        var createdOrder = orderService.save(order);

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getCreatedAt());
        assertEquals(OrderStatus.OPENED, createdOrder.getStatus());
    }
}
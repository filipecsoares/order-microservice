package com.simpledev.order.service;

import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Item;
import com.simpledev.order.model.Order;
import com.simpledev.order.protocols.OrderRequest;
import com.simpledev.order.protocols.ProductRequest;
import com.simpledev.order.repository.ItemRepository;
import com.simpledev.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    private Order mockOrder;

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void shouldSaveAnValidOrder() {
        var userId = 1L;
        var product = ProductRequest.builder().quantity(1).id(1L).price(BigDecimal.valueOf(20.0)).build();
        var orderRequest = OrderRequest.builder().userId(userId).products(Arrays.asList(product)).build();
        var fakeOrder = Order.builder().build();

        when(orderRepository.save(any(Order.class))).thenReturn(fakeOrder);
        when(itemRepository.save(any())).thenReturn(new Item());
        var createdOrder = orderService.save(orderRequest);

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getCreatedAt());
        assertEquals(OrderStatus.PENDING, createdOrder.getStatus());
        assertEquals(BigDecimal.valueOf(20.0), createdOrder.getTotal());
    }

    @Test()
    public void shouldNotSaveAnOrderWithoutUser() {
        var userId = 1L;
        var product = ProductRequest.builder().quantity(1).id(1L).price(BigDecimal.valueOf(20.0)).build();
        var orderRequest = OrderRequest.builder().products(Arrays.asList(product)).build();
        var fakeOrder = Order.builder().build();

        when(orderRepository.save(any(Order.class))).thenReturn(fakeOrder);
        when(itemRepository.save(any())).thenReturn(new Item());
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.save(orderRequest);
        });
    }
}
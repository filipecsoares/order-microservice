package com.simpledev.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpledev.order.enums.OrderStatus;
import com.simpledev.order.model.Order;
import com.simpledev.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService service;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnCreatedStatusOnPostRequest() throws Exception {
        LocalDateTime createdAt = LocalDateTime.now();
        Order validOrder = new Order();
        Order returnedOrder = Order.builder().id(1L).status(OrderStatus.OPENED).createdAt(createdAt).build();
        when(service.save(any(Order.class))).thenReturn(returnedOrder);
        mockMvc.perform(post("/api/v1/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrder))).andExpect(status().isCreated());
        verify(service, times(1)).save(any(Order.class));
    }
}
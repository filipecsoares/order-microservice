package com.simpledev.order.protocols;

import com.simpledev.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private BigDecimal total;
    private OrderStatus status;
    private List<ProductResponse> products;
    private UserResponse user;
    private LocalDateTime createdAt;
}

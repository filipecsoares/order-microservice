package com.simpledev.common.events;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderCreatedEvent extends BaseEvent {
    private Long id;
    private Long userId;
    private BigDecimal total;
    private LocalDateTime createdAt;
}

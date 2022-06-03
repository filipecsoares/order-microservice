package com.simpledev.payment.service;

import com.simpledev.payment.model.Payment;

public interface PaymentService {
    public Payment payOrder(Long orderId);
}

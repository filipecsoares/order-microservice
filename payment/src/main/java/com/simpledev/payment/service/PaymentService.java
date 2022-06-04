package com.simpledev.payment.service;

import com.simpledev.payment.exception.PaymentNotFoundException;
import com.simpledev.payment.model.Payment;

public interface PaymentService {
    public Payment payOrder(Long orderId) throws PaymentNotFoundException;
}

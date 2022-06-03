package com.simpledev.payment.service;

import com.simpledev.payment.model.Payment;
import com.simpledev.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Override
    public Payment payOrder(Long orderId) {
        //Payment payment = paymentRepository.save();
        //return payment;
        return null;
    }
}

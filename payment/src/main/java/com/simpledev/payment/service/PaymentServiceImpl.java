package com.simpledev.payment.service;

import com.simpledev.payment.enums.PaymentStatus;
import com.simpledev.payment.exception.PaymentNotFoundException;
import com.simpledev.payment.model.Payment;
import com.simpledev.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Override
    public Payment payOrder(Long orderId) throws PaymentNotFoundException {
        var optionalPayment = paymentRepository.getByOrderId(orderId);
        var payment = optionalPayment.orElseThrow(PaymentNotFoundException::new);
        payment.setStatus(PaymentStatus.PAID);
        return payment;
    }
}

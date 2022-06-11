package com.simpledev.payment.controller;

import com.simpledev.payment.codec.Codec;
import com.simpledev.payment.exception.PaymentNotFoundException;
import com.simpledev.payment.model.Payment;
import com.simpledev.payment.protocols.PaymentResponse;
import com.simpledev.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> payOrder(@PathVariable Long orderId) throws PaymentNotFoundException {
        Payment payment = paymentService.payOrder(orderId);
        return new ResponseEntity<>(Codec.toResponse(payment), HttpStatus.CREATED);
    }
}

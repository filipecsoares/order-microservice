package com.simpledev.payment.codec;

import com.simpledev.payment.model.Payment;
import com.simpledev.payment.protocols.PaymentResponse;
import org.springframework.beans.BeanUtils;

public class Codec {
    public static PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        BeanUtils.copyProperties(payment, response);
        return response;
    }
}

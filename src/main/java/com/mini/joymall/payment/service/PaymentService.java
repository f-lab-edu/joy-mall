package com.mini.joymall.payment.service;

import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);
}

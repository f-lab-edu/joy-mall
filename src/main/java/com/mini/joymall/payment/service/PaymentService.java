package com.mini.joymall.payment.service;

import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.response.ApprovePaymentResponse;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;

import java.util.Map;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);
    ApprovePaymentResponse approvePayment(Map<String, String> params);
}

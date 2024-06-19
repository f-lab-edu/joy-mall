package com.mini.joymall.payment.controller;

import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.response.ApprovePaymentResponse;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<CreatePaymentResponse> create(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return OK(paymentService.createPayment(createPaymentRequest));
    }

    @GetMapping("/payment/success")
    public ResponseEntity<ApprovePaymentResponse> pgPaymentSuccess(@RequestParam Map<String, String> params) {
        return OK(paymentService.approvePayment(params));
    }
}

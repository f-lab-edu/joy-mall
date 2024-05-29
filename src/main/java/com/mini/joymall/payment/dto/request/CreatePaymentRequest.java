package com.mini.joymall.payment.dto.request;

import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class CreatePaymentRequest {
    private Long orderId;
    private int amount;
    private PaymentMethod paymentMethod;

    @Builder
    public CreatePaymentRequest(Long orderId, int amount, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public Payment toEntity() {
        Set<PaymentHistory> paymentHistories = new HashSet<>();
        paymentHistories.add(PaymentHistory.waiting(amount, paymentMethod));

        return Payment.builder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .orderId(orderId)
                .paymentHistories(paymentHistories)
                .build();
    }

    public Payment completePayment(Payment payment) {
        payment.complete(amount, paymentMethod);
        return payment;
    }

}

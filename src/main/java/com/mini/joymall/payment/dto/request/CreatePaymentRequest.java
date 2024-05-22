package com.mini.joymall.payment.dto.request;

import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        return Payment.builder()
                .amount(amount)
                .paymentMethod(paymentMethod)
                .paymentStatus(PaymentStatus.WAITING)
                .paymentDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .orderId(orderId)
                .build();
    }

}

package com.mini.joymall.payment.dto.response;

import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
public class CreatePaymentResponse {
    private Long id;
    private Set<PaymentHistory> paymentHistories;
    private Object data;

    @Builder
    public CreatePaymentResponse(Long id, Set<PaymentHistory> paymentHistories, Object data) {
        this.id = id;
        this.paymentHistories = paymentHistories;
        this.data = data;
    }

    public static CreatePaymentResponse from(Payment payment, Object data) {
        return CreatePaymentResponse.builder()
                .id(payment.getId())
                .paymentHistories(payment.getPaymentHistories())
                .data(data)
                .build();
    }
}

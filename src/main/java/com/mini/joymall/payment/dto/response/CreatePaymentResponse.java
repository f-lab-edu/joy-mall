package com.mini.joymall.payment.dto.response;

import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreatePaymentResponse<T> {
    private Long id;
    private int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private Object data;

    @Builder
    public CreatePaymentResponse(Long id, int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime paymentDate, Object data) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.data = data;
    }

    public static CreatePaymentResponse from(Payment payment, Object data) {
        return CreatePaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .data(data)
                .build();
    }
}

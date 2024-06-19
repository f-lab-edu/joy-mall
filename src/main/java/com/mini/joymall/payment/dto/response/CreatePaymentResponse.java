package com.mini.joymall.payment.dto.response;

import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private Long id;
    private Set<PaymentHistory> paymentHistories;
    private PayReadyResponse payReadyResponse;

    public static CreatePaymentResponse from(Payment payment, PayReadyResponse payReadyResponse) {
        return CreatePaymentResponse.builder()
                .id(payment.getId())
                .paymentHistories(payment.getPaymentHistories())
                .payReadyResponse(payReadyResponse)
                .build();
    }

}


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
public class ApprovePaymentResponse {
    private Long id;
    private Set<PaymentHistory> paymentHistories;
    private PayApproveResponse payApproveResponse;

    public static ApprovePaymentResponse from(Payment payment, PayApproveResponse payApproveResponse) {
        return ApprovePaymentResponse.builder()
                .id(payment.getId())
                .paymentHistories(payment.getPaymentHistories())
                .payApproveResponse(payApproveResponse)
                .build();
    }
}

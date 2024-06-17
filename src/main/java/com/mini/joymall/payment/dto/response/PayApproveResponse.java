package com.mini.joymall.payment.dto.response;

import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.dto.response.kakao.KakaoPayApproveApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PayApproveResponse {
    private String pgPaymentId;
    private String clientId;
    private int totalAmount;

    private PaymentMethod paymentMethod;

    public static PayApproveResponse from(KakaoPayApproveApiResponse kakaoPayApproveApiResponse, PaymentMethod paymentMethod) {
        return PayApproveResponse.builder()
                .pgPaymentId(kakaoPayApproveApiResponse.getTid())
                .clientId(kakaoPayApproveApiResponse.getCid())
                .totalAmount(kakaoPayApproveApiResponse.getAmount().getTotal())
                .paymentMethod(paymentMethod)
                .build();
    }
}

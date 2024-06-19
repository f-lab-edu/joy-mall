package com.mini.joymall.payment.domain.entity;

import com.mini.joymall.payment.dto.request.kakao.KakaoPayReadyApiRequest;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor
@Table("PG_PAY_HISTORY")
public class PgPayHistory {
    @Id
    @Column("PG_PAY_HISTORY_ID")
    private Long id;
    private String pgPaymentId;
    private String clientId;
    private String orderId;
    private String userId;
    private PaymentMethod paymentMethod;

    @Builder
    public PgPayHistory(String pgPaymentId, String clientId, String orderId, String userId, PaymentMethod paymentMethod) {
        this.pgPaymentId = pgPaymentId;
        this.clientId = clientId;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
    }

    public static PgPayHistory from(KakaoPayReadyApiRequest kakaoPayReadyApiRequest, PayReadyResponse payReadyResponse, PaymentMethod paymentMethod) {
        return PgPayHistory.builder()
                .pgPaymentId(payReadyResponse.getPgPaymentId())
                .clientId(kakaoPayReadyApiRequest.getCid())
                .orderId(kakaoPayReadyApiRequest.getPartnerOrderId())
                .userId(kakaoPayReadyApiRequest.getPartnerUserId())
                .paymentMethod(paymentMethod)
                .build();
    }
}

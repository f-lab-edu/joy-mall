package com.mini.joymall.payment.domain.entity;

import com.mini.joymall.payment.dto.request.kakao.KakaoPayReadyApiRequest;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("KAKAO_PAY_HISTORY")
public class KakaoPayHistory {
    @Id
    private Long id;
    private String cid;
    private String partnerOrderId;
    private String partnerUserId;
    private String tid;

    @Builder
    public KakaoPayHistory(String cid, String partnerOrderId, String partnerUserId, String tid) {
        this.cid = cid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.tid = tid;
    }

    public static KakaoPayHistory from(KakaoPayReadyApiRequest kakaoPayReadyApiRequest, PayReadyResponse payReadyResponse) {
        return KakaoPayHistory.builder()
                .cid(kakaoPayReadyApiRequest.getCid())
                .partnerOrderId(kakaoPayReadyApiRequest.getPartnerOrderId())
                .partnerUserId(kakaoPayReadyApiRequest.getPartnerUserId())
                .tid(payReadyResponse.getPgPaymentId())
                .build();
    }
}

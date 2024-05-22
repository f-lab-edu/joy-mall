package com.mini.joymall.payment.domain.entity;

import com.mini.joymall.payment.dto.request.KakaoReadyRequest;
import com.mini.joymall.payment.dto.response.KakaoReadyResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor
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

    public static KakaoPayHistory from(KakaoReadyRequest kakaoReadyRequest, KakaoReadyResponse kakaoReadyResponse) {
        return KakaoPayHistory.builder()
                .cid(kakaoReadyRequest.getCid())
                .partnerOrderId(kakaoReadyRequest.getPartnerOrderId())
                .partnerUserId(kakaoReadyRequest.getPartnerUserId())
                .tid(kakaoReadyResponse.getTid())
                .build();
    }
}

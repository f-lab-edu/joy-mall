package com.mini.joymall.payment.dto.request.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.joymall.payment.domain.entity.PgPayHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoPayApproveApiRequest {
    @JsonProperty("tid")
    private String tid;

    @JsonProperty("cid")
    private String cid;

    @JsonProperty("partner_order_id")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    private String partnerUserId;

    @JsonProperty("pg_token")
    private String pgToken;

    @Builder
    public KakaoPayApproveApiRequest(String tid, String cid, String partnerOrderId, String partnerUserId, String pgToken) {
        this.tid = tid;
        this.cid = cid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.pgToken = pgToken;
    }

    public static KakaoPayApproveApiRequest from(PgPayHistory pgPayHistory, String pgToken) {
        return KakaoPayApproveApiRequest.builder()
                .tid(pgPayHistory.getPgPaymentId())
                .cid(pgPayHistory.getClientId())
                .partnerOrderId(pgPayHistory.getOrderId())
                .partnerUserId(pgPayHistory.getUserId())
                .pgToken(pgToken)
                .build();
    }
}

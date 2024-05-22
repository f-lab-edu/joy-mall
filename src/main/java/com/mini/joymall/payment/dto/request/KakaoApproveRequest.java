package com.mini.joymall.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.payment.domain.entity.KakaoPayHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoApproveRequest {
    @JsonProperty("cid")
    private String cid;

    @JsonProperty("tid")
    private String tid;

    @JsonProperty("partner_order_id")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    private String partnerUserId;

    @JsonProperty("pg_token")
    private String pgToken;

    @Builder
    public KakaoApproveRequest(String cid, String tid, String partnerOrderId, String partnerUserId, String pgToken) {
        this.cid = cid;
        this.tid = tid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.pgToken = pgToken;
    }

    public static KakaoApproveRequest from(KakaoPayHistory kakaoPayHistory, String pgToken) {
        return KakaoApproveRequest.builder()
                .cid(kakaoPayHistory.getCid())
                .tid(kakaoPayHistory.getTid())
                .partnerOrderId(kakaoPayHistory.getPartnerOrderId())
                .partnerUserId(kakaoPayHistory.getPartnerUserId())
                .pgToken(pgToken)
                .build();
    }

    public String convertToJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("KakaoApproveRequest 객체를 Json 변환에 실패하였습니다.", e);
        }
    }
}

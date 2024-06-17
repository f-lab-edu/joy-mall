package com.mini.joymall.payment.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class KakaoPayApproveApiResponse {
    @JsonProperty("aid")
    private String aid;

    @JsonProperty("tid")
    private String tid;

    @JsonProperty("cid")
    private String cid;

    @JsonProperty("partner_order_id")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    private String partnerUserId;

    @JsonProperty("payment_method_type")
    private String paymentMethodType;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("approved_at")
    private String approvedAt;

    @Getter
    @AllArgsConstructor
    public static class Amount {
        private int total;
        private int taxFree;
        private int vat;
        private int point;
        private int discount;
        private int greenDeposit;
    }

    public static KakaoPayApproveApiResponse from(ResponseEntity<KakaoPayApproveApiResponse> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("결제 준비 요청에 실패했습니다.");
        }
        if (response.getBody() == null) {
            throw new RuntimeException("결제 준비 응답 데이터가 null입니다.");
        }
        return response.getBody();
    }

}

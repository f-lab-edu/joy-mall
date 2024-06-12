package com.mini.joymall.payment.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class PayApproveResponse {
    private String pgPaymentId;
    private String clientId;
    private int totalAmount;
    private PaymentMethod paymentMethod;

    public static PayApproveResponse fromKakaoPay(ResponseEntity<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return PayApproveResponse.builder()
                    .pgPaymentId(jsonNode.get("tid").asText())
                    .clientId(jsonNode.get("cid").asText())
                    .totalAmount(jsonNode.get("amount").get("total").asInt())
                    .paymentMethod(PaymentMethod.KAKAOPAY)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("PayApproveResponse 로 변환하는데 실패하였습니다.", e);
        }
    }
}

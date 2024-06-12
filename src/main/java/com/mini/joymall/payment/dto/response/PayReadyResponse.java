package com.mini.joymall.payment.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class PayReadyResponse {
    private String pgPaymentId;
    private String redirectAppUrl;
    private String redirectMobileUrl;
    private String redirectPcUrl;
    private String androidAppScheme;
    private String iosAppScheme;

    public static PayReadyResponse fromKakaoPay(ResponseEntity<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return PayReadyResponse.builder()
                    .pgPaymentId(jsonNode.get("tid").asText())
                    .redirectAppUrl(jsonNode.get("next_redirect_app_url").asText())
                    .redirectMobileUrl(jsonNode.get("next_redirect_mobile_url").asText())
                    .redirectPcUrl(jsonNode.get("next_redirect_pc_url").asText())
                    .androidAppScheme(jsonNode.get("android_app_scheme").asText())
                    .iosAppScheme(jsonNode.get("ios_app_scheme").asText())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("PayReadyResponse 로 변환하는데 실패하였습니다.", e);
        }
    }
}

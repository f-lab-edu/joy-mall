package com.mini.joymall.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.payment.dto.response.kakao.KakaoPayReadyApiResponse;
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

    public static PayReadyResponse from(KakaoPayReadyApiResponse kakaoPayReadyApiResponse) {
        return PayReadyResponse.builder()
                .pgPaymentId(kakaoPayReadyApiResponse.getTid())
                .redirectAppUrl(kakaoPayReadyApiResponse.getNextRedirectAppUrl())
                .redirectMobileUrl(kakaoPayReadyApiResponse.getNextRedirectMobileUrl())
                .redirectPcUrl(kakaoPayReadyApiResponse.getNextRedirectPcUrl())
                .androidAppScheme(kakaoPayReadyApiResponse.getAndroidAppScheme())
                .build();
    }
}

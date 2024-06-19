package com.mini.joymall.payment.dto.response.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class KakaoPayReadyApiResponse {
    @JsonProperty("tid")
    private String tid;
    @JsonProperty("next_redirect_app_url")
    private String nextRedirectAppUrl;
    @JsonProperty("next_redirect_mobile_url")
    private String nextRedirectMobileUrl;
    @JsonProperty("next_redirect_pc_url")
    private String nextRedirectPcUrl;
    @JsonProperty("android_app_scheme")
    private String androidAppScheme;
    @JsonProperty("ios_app_scheme")
    private String iosAppScheme;

    public static KakaoPayReadyApiResponse from(ResponseEntity<KakaoPayReadyApiResponse> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("결제 준비 요청에 실패했습니다.");
        }
        if (response.getBody() == null) {
            throw new RuntimeException("결제 준비 응답 데이터가 null입니다.");
        }
        return response.getBody();
    }
}

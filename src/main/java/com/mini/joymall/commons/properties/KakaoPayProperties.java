package com.mini.joymall.commons.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Getter
@ConfigurationProperties(prefix = "kakao-pay")
@RequiredArgsConstructor
public class KakaoPayProperties {
    private final String host;
    private final String cid;
    private final String secretKey;

    public HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.set("Authorization", secretKey);
        httpHeaders.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }
}

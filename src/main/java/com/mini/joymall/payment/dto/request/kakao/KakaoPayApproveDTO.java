package com.mini.joymall.payment.dto.request.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class KakaoPayApproveDTO {
    private String orderId;
    private String pgToken;

    public static KakaoPayApproveDTO from(Map<String, String> params) {
        if (params.get("orderId") == null || params.get("pg_token") == null) {
            throw new IllegalArgumentException("파라미터가 존재하지 않습니다.");
        }

        return new KakaoPayApproveDTO(params.get("orderId"), params.get("pg_token"));
    }
}

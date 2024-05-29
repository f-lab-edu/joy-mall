package com.mini.joymall.payment.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mini.joymall.payment.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoService kakaoService;

    @GetMapping("/kakaoPay/success")
    public ResponseEntity<JsonNode> kakaoPaySuccess(@RequestParam("partner_order_id") String partnerOrderId, @RequestParam("pg_token") String pgToken) {
        return OK(kakaoService.approve(partnerOrderId, pgToken));
    }
}

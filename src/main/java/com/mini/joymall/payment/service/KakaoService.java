package com.mini.joymall.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.commons.properties.KakaoPayProperties;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.KakaoPayHistory;
import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.repository.KakaoPayHistoryRepository;
import com.mini.joymall.payment.domain.repository.PaymentRepository;
import com.mini.joymall.payment.dto.request.KakaoApproveRequest;
import com.mini.joymall.payment.dto.request.KakaoReadyRequest;
import com.mini.joymall.payment.dto.response.KakaoReadyResponse;
import com.mini.joymall.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoPayProperties kakaoPayProperties;
    private final KakaoPayHistoryRepository kakaoPayHistoryRepository;
    private final PaymentRepository paymentRepository;
    private final CategoryService categoryService;

    public KakaoReadyResponse ready(Order order) {
        KakaoReadyRequest kakaoReadyRequest = KakaoReadyRequest.create(kakaoPayProperties, order);
        HttpEntity<String> request = new HttpEntity<>(kakaoReadyRequest.convertToJson(), kakaoPayProperties.createHeaders());
        KakaoReadyResponse kakaoReadyResponse = new RestTemplate().postForObject(kakaoPayProperties.getHost() + "/online/v1/payment/ready", request, KakaoReadyResponse.class);
        kakaoPayHistoryRepository.save(KakaoPayHistory.from(kakaoReadyRequest, kakaoReadyResponse));
        return kakaoReadyResponse;
    }

    public JsonNode approve(String partnerOrderId, String pgToken) {
        KakaoPayHistory kakaoPayHistory = kakaoPayHistoryRepository.findByPartnerOrderId(partnerOrderId).orElseThrow(NoSuchElementException::new);
        KakaoApproveRequest kakaoApproveRequest = KakaoApproveRequest.from(kakaoPayHistory, pgToken);
        HttpEntity<String> request = new HttpEntity<>(kakaoApproveRequest.convertToJson(), kakaoPayProperties.createHeaders());
        ResponseEntity<String> response = new RestTemplate().exchange(
                kakaoPayProperties.getHost() + "/online/v1/payment/approve",
                HttpMethod.POST,
                request,
                String.class);

        JsonNode jsonNode;
        try {
            jsonNode = new ObjectMapper().readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        int amount = jsonNode.get("amount").get("total").asInt();

        Payment payment = paymentRepository.findByOrderId(partnerOrderId).orElseThrow(NoSuchElementException::new);
        switch (response.getStatusCode().value()) {
            case 200:
                paymentRepository.save(payment.complete(amount, PaymentMethod.KAKAOPAY));
                break;
            case 400:
                paymentRepository.save(payment.failed(amount, PaymentMethod.KAKAOPAY));
                break;
        }
        return jsonNode;
    }
}

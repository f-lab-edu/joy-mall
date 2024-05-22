package com.mini.joymall.payment.service;

import com.mini.joymall.commons.properties.KakaoPayProperties;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.KakaoPayHistory;
import com.mini.joymall.payment.domain.repository.KakaoPayHistoryRepository;
import com.mini.joymall.payment.dto.request.KakaoApproveRequest;
import com.mini.joymall.payment.dto.request.KakaoReadyRequest;
import com.mini.joymall.payment.dto.response.KakaoReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoPayProperties kakaoPayProperties;
    private final KakaoPayHistoryRepository kakaoPayHistoryRepository;

    public KakaoReadyResponse ready(Order order) {
        KakaoReadyRequest kakaoReadyRequest = KakaoReadyRequest.create(kakaoPayProperties, order);
        HttpEntity<String> request = new HttpEntity<>(kakaoReadyRequest.convertToJson(), kakaoPayProperties.createHeaders());
        KakaoReadyResponse kakaoReadyResponse = new RestTemplate().postForObject(kakaoPayProperties.getHost() + "/online/v1/payment/ready", request, KakaoReadyResponse.class);
        kakaoPayHistoryRepository.save(KakaoPayHistory.from(kakaoReadyRequest, kakaoReadyResponse));
        return kakaoReadyResponse;
    }

    public Object approve(String partnerOrderId, String pgToken) {
        KakaoPayHistory kakaoPayHistory = kakaoPayHistoryRepository.findByPartnerOrderId(partnerOrderId).orElseThrow(NoSuchElementException::new);
        KakaoApproveRequest kakaoApproveRequest = KakaoApproveRequest.from(kakaoPayHistory, pgToken);
        HttpEntity<String> request = new HttpEntity<>(kakaoApproveRequest.convertToJson(), kakaoPayProperties.createHeaders());
        Object object = new RestTemplate().postForObject(kakaoPayProperties.getHost() + "/online/v1/payment/approve", request, Object.class);
        return object;
    }
}

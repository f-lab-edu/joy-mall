package com.mini.joymall.payment.service.kakao;

import com.mini.joymall.commons.properties.KakaoPayProperties;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.PgPayHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.repository.PgPayHistoryRepository;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayApproveApiRequest;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayApproveDTO;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayReadyApiRequest;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import com.mini.joymall.payment.dto.response.kakao.KakaoPayApproveApiResponse;
import com.mini.joymall.payment.dto.response.kakao.KakaoPayReadyApiResponse;
import com.mini.joymall.payment.service.PayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
public class KakaoPayClient implements PayClient {

    private final RestTemplate restTemplate;
    private final KakaoPayProperties kakaoPayProperties;
    private final PgPayHistoryRepository pgPayHistoryRepository;

    @Override
    public boolean isAvailAble(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.KAKAOPAY;
    }

    @Override
    public PayReadyResponse ready(Order order) {
        KakaoPayReadyApiRequest kakaoPayReadyApiRequest = KakaoPayReadyApiRequest.create(kakaoPayProperties, order);
        HttpEntity<KakaoPayReadyApiRequest> request = new HttpEntity<>(kakaoPayReadyApiRequest, kakaoPayProperties.createHeaders());
        ResponseEntity<KakaoPayReadyApiResponse> response = restTemplate.postForEntity(kakaoPayProperties.getHost() + "/online/v1/payment/ready", request, KakaoPayReadyApiResponse.class);
        KakaoPayReadyApiResponse kakaoPayReadyApiResponse = KakaoPayReadyApiResponse.from(response);
        PayReadyResponse payReadyResponse = PayReadyResponse.from(kakaoPayReadyApiResponse);
        pgPayHistoryRepository.save(PgPayHistory.from(kakaoPayReadyApiRequest, payReadyResponse, PaymentMethod.KAKAOPAY));
        return payReadyResponse;
    }

    @Override
    public PayApproveResponse approve(Map<String, String> params) {
        KakaoPayApproveDTO kakaoPayApproveDTO = KakaoPayApproveDTO.from(params);
        PgPayHistory pgPayHistory = pgPayHistoryRepository.findByOrderId(kakaoPayApproveDTO.getOrderId()).orElseThrow(NoSuchElementException::new);
        KakaoPayApproveApiRequest kakaoPayApproveApiRequest = KakaoPayApproveApiRequest.from(pgPayHistory, kakaoPayApproveDTO.getPgToken());
        HttpEntity<KakaoPayApproveApiRequest> request = new HttpEntity<>(kakaoPayApproveApiRequest, kakaoPayProperties.createHeaders());
        ResponseEntity<KakaoPayApproveApiResponse> response = restTemplate.postForEntity(kakaoPayProperties.getHost() + "/online/v1/payment/approve", request, KakaoPayApproveApiResponse.class);
        KakaoPayApproveApiResponse kakaoPayApproveApiResponse = KakaoPayApproveApiResponse.from(response);
        return PayApproveResponse.from(kakaoPayApproveApiResponse, PaymentMethod.KAKAOPAY);
    }
}
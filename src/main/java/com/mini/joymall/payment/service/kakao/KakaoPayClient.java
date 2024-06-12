package com.mini.joymall.payment.service.kakao;

import com.mini.joymall.commons.properties.KakaoPayProperties;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.KakaoPayHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.repository.KakaoPayHistoryRepository;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayApproveApiRequest;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayApproveDTO;
import com.mini.joymall.payment.dto.request.kakao.KakaoPayReadyApiRequest;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import com.mini.joymall.payment.service.PayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
    private final KakaoPayHistoryRepository kakaoPayHistoryRepository;

    @Override
    public boolean isAvailAble(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.KAKAOPAY;
    }

    public PayReadyResponse ready(Order order) {
        KakaoPayReadyApiRequest kakaoPayReadyApiRequest = KakaoPayReadyApiRequest.create(kakaoPayProperties, order);
        HttpEntity<String> request = new HttpEntity<>(kakaoPayReadyApiRequest.convertToJson(), kakaoPayProperties.createHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(kakaoPayProperties.getHost() + "/online/v1/payment/ready", request, String.class);

        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new RuntimeException("결제 준비 요청에 실패했습니다.");
        }

        PayReadyResponse payReadyResponse = PayReadyResponse.fromKakaoPay(response);
        kakaoPayHistoryRepository.save(KakaoPayHistory.from(kakaoPayReadyApiRequest, payReadyResponse));
        return payReadyResponse;
    }

    @Override
    public PayApproveResponse approve(Map<String, String> params) {
        KakaoPayApproveDTO kakaoPayApproveDTO = KakaoPayApproveDTO.from(params);
        KakaoPayHistory kakaoPayHistory = kakaoPayHistoryRepository.findByPartnerOrderId(kakaoPayApproveDTO.getOrderId()).orElseThrow(NoSuchElementException::new);
        KakaoPayApproveApiRequest kakaoPayApproveApiRequest = KakaoPayApproveApiRequest.from(kakaoPayHistory, kakaoPayApproveDTO.getPgToken());
        HttpEntity<String> request = new HttpEntity<>(kakaoPayApproveApiRequest.convertToJson(), kakaoPayProperties.createHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(kakaoPayProperties.getHost() + "/online/v1/payment/approve", request, String.class);

        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new RuntimeException("결제 승인 요청에 실패했습니다.");
        }
        return PayApproveResponse.fromKakaoPay(response);
    }
}

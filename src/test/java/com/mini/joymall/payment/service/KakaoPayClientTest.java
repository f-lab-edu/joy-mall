package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import com.mini.joymall.payment.service.kakao.KakaoPayClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class KakaoPayClientTest {
    @Autowired
    private KakaoPayClient kakaoPayClient;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 카카오페이_준비_API_연동_성공() {
        // given
        OrderItem orderItem = new OrderItem(1L, 10, 1000);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem);

        Set<OrderHistory> orderHistories = new HashSet<>();
        orderHistories.add(OrderHistory.pending());
        Order savedOrder = orderRepository.save(new Order(1L, orderItems, orderHistories));

        // when
        PayReadyResponse payReadyResponse = kakaoPayClient.ready(savedOrder);

        // then
        assertThat(payReadyResponse.getPgPaymentId()).startsWith("T");
        assertThat(payReadyResponse.getRedirectAppUrl()).startsWith("https://online-pay.kakao.com");
        assertThat(payReadyResponse.getRedirectMobileUrl()).startsWith("https://online-pay.kakao.com");
        assertThat(payReadyResponse.getRedirectPcUrl()).startsWith("https://online-pay.kakao.com");
        assertThat(payReadyResponse.getAndroidAppScheme()).startsWith("kakaotalk://kakaopay/pg?url=");
        assertThat(payReadyResponse.getIosAppScheme()).startsWith("kakaotalk://kakaopay/pg?url=");
    }
}
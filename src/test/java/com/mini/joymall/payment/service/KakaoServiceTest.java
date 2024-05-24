package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.payment.dto.response.KakaoReadyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KakaoServiceTest {

    @Autowired
    private KakaoService kakaoService;

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
        KakaoReadyResponse kakaoReadyResponse = kakaoService.ready(savedOrder);

        // then
        assertNotNull(kakaoReadyResponse);
    }
}
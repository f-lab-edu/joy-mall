package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentHistory;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import com.mini.joymall.payment.domain.repository.PaymentRepository;
import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import com.mini.joymall.payment.service.kakao.KakaoPayClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void 카카오_페이로_결제_요청_성공() {
        // given
        OrderItem orderItem1 = new OrderItem(1L, 10, 1000);
        OrderItem orderItem2 = new OrderItem(1L, 30, 500);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        Set<OrderHistory> orderHistories = new HashSet<>();
        orderHistories.add(OrderHistory.pending());
        Long orderId = orderRepository.save(new Order(1L, orderItems, orderHistories))
                .getId();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(orderId, 25000, PaymentMethod.KAKAOPAY);

        // when
        CreatePaymentResponse createPaymentResponse = paymentService.createPayment(createPaymentRequest);

        Long paymentId = createPaymentResponse.getId();
        Payment savedPayment = paymentRepository.findById(paymentId).orElseThrow(NoSuchElementException::new);
        Set<PaymentHistory> paymentHistories = savedPayment.getPaymentHistories();
        PaymentHistory paymentHistory = paymentHistories.stream()
                .filter(history -> history.getPaymentMethod().equals(PaymentMethod.KAKAOPAY))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(createPaymentResponse.getPayReadyResponse()).isInstanceOf(PayReadyResponse.class);
        assertThat(savedPayment.getOrderId()).isEqualTo(orderId);

        assertThat(paymentHistory.getAmount()).isEqualTo(25000);
        assertThat(paymentHistory.getPaymentMethod()).isEqualTo(PaymentMethod.KAKAOPAY);
        assertThat(paymentHistory.getPaymentStatus()).isEqualTo(PaymentStatus.WAITING);
    }
}
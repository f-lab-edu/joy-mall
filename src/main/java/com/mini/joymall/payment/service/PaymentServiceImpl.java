package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.repository.PaymentRepository;
import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final KakaoService kakaoService;

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        Order order = orderRepository.findById(createPaymentRequest.getOrderId()).orElseThrow(NoSuchElementException::new);

        Payment payment = paymentRepository.save(createPaymentRequest.toEntity());

        switch (createPaymentRequest.getPaymentMethod()) {
            case KAKAOPAY:
                return CreatePaymentResponse.from(payment, kakaoService.ready(order));
            default:
                throw new RuntimeException("결제수단을 확인해주세요.");
        }

    }
}

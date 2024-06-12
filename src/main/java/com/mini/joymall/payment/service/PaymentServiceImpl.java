package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.repository.OrderRepository;
import com.mini.joymall.payment.domain.entity.Payment;
import com.mini.joymall.payment.domain.entity.PaymentHistory;
import com.mini.joymall.payment.domain.entity.PaymentStatus;
import com.mini.joymall.payment.domain.repository.PaymentRepository;
import com.mini.joymall.payment.dto.request.CreatePaymentRequest;
import com.mini.joymall.payment.dto.request.PayApproveDTO;
import com.mini.joymall.payment.dto.response.ApprovePaymentResponse;
import com.mini.joymall.payment.dto.response.CreatePaymentResponse;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentValidator paymentValidator;
    private final PaymentRepository paymentRepository;
    private final List<PayClient> payClients;

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        Order order = orderRepository.findById(createPaymentRequest.getOrderId()).orElseThrow(NoSuchElementException::new);

        Payment payment = createPaymentRequest.toEntity();
        paymentValidator.validate(payment);
        paymentRepository.save(payment.waiting(createPaymentRequest.getAmount(), createPaymentRequest.getPaymentMethod()));

        PayClient payClient = payClients.stream()
                .filter(client -> client.isAvailAble(createPaymentRequest.getPaymentMethod()))
                .findFirst()
                .orElseThrow(() -> {
                    paymentRepository.save(payment.failed(createPaymentRequest.getAmount(), createPaymentRequest.getPaymentMethod()));
                    return new RuntimeException("결제수단을 확인해주세요.");
                });

        PayReadyResponse payReadyResponse = payClient.ready(order);
        return CreatePaymentResponse.from(payment, payReadyResponse);
    }

    @Override
    public ApprovePaymentResponse approvePayment(Map<String, String> params) {
        PayApproveDTO payApproveDTO = PayApproveDTO.from(params);

        Payment payment = paymentRepository.findByOrderId(payApproveDTO.getOrderId()).orElseThrow(NoSuchElementException::new);

        PaymentHistory paymentHistory = payment.getPaymentHistories().stream()
                .filter(history -> history.getPaymentStatus() == PaymentStatus.WAITING)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        paymentRepository.save(payment.requesting(paymentHistory.getAmount(), paymentHistory.getPaymentMethod()));;

        PayClient payClient = payClients.stream()
                .filter(client -> client.isAvailAble(payApproveDTO.getPaymentMethod()))
                .findFirst()
                .orElseThrow(() -> {
                    paymentRepository.save(payment.failed(paymentHistory.getAmount(), paymentHistory.getPaymentMethod()));
                    return new RuntimeException("결제 수단을 확인해주세요.");
                });

        PayApproveResponse payApproveResponse = payClient.approve(params);
        paymentRepository.save(payment.complete(payApproveResponse.getTotalAmount(), payApproveResponse.getPaymentMethod()));
        return ApprovePaymentResponse.from(payment, payApproveResponse);
    }
}

package com.mini.joymall.payment.domain.entity;

import com.mini.joymall.order.domain.entity.Order;

import java.time.LocalDateTime;

public class Payment {
    private String id;
    private Integer amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;
    private Order order;
}

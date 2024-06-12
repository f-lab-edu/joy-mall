package com.mini.joymall.payment.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;

import java.util.Map;

public interface PayClient {
    boolean isAvailAble(PaymentMethod paymentMethod);

    PayReadyResponse ready(Order order);

    PayApproveResponse approve(Map<String, String> params);
}

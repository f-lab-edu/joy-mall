package com.mini.joymall.payment.service.naver;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.payment.domain.entity.PaymentMethod;
import com.mini.joymall.payment.dto.response.PayApproveResponse;
import com.mini.joymall.payment.dto.response.PayReadyResponse;
import com.mini.joymall.payment.service.PayClient;

import java.util.Map;

public class NaverPayClient implements PayClient {
    @Override
    public boolean isAvailAble(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.NAVERPAY;
    }

    @Override
    public PayReadyResponse ready(Order order) {
        return null;
    }

    @Override
    public PayApproveResponse approve(Map<String, String> params) {
        return null;
    }
}

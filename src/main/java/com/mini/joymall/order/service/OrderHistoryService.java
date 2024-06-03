package com.mini.joymall.order.service;

import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderStatus;

public interface OrderHistoryService {
    OrderHistory createHistory(Long orderId, OrderStatus orderStatus);
}

package com.mini.joymall.order.service;

import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderStatus;
import com.mini.joymall.order.domain.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public OrderHistory createHistory(Long orderId, OrderStatus orderStatus) {
        return orderHistoryRepository.save(new OrderHistory(orderId, orderStatus));
    }
}

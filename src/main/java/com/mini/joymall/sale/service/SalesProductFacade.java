package com.mini.joymall.sale.service;

import com.mini.joymall.order.domain.entity.OrderItem;

import java.util.Set;

public interface SalesProductFacade {
    void decreaseStock(Set<OrderItem> orderItems);
}

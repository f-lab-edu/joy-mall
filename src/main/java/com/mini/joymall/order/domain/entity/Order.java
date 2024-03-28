package com.mini.joymall.order.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;

}

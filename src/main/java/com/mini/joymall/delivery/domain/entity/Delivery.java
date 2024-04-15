package com.mini.joymall.delivery.domain.entity;

import com.mini.joymall.order.domain.entity.Order;

import java.time.LocalDateTime;

public class Delivery {
    private Long id;
    private DeliveryStatus deliveryStatus;
    private String trackingNumber;
    private LocalDateTime shipmentDate;
    private LocalDateTime estimateDate;
    private LocalDateTime arrivalDate;
    private Order order;
}

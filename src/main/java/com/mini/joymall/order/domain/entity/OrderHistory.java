package com.mini.joymall.order.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("ORDER_HISTORY")
@Getter
@NoArgsConstructor
public class OrderHistory {

    @Id
    @Column("ORDER_HISTORY_ID")
    private Long id;
    private Long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime createdDate;

    public OrderHistory(Long orderId, OrderStatus orderStatus) {
        this(orderId, orderStatus, LocalDateTime.now());
    }

    public OrderHistory(Long orderId, OrderStatus orderStatus, LocalDateTime createdDate) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
    }
}

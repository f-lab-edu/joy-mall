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
    private OrderStatus orderStatus;
    private LocalDateTime createdDate;

    public OrderHistory(OrderStatus orderStatus) {
        this(orderStatus, LocalDateTime.now());
    }

    @Builder
    public OrderHistory(OrderStatus orderStatus, LocalDateTime createdDate) {
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
    }

    public static OrderHistory pending() {
        return new OrderHistory(OrderStatus.PENDING, LocalDateTime.now());
    }
}

package com.mini.joymall.order.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table("ORDER")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Order {
    @Id
    @Column("ORDER_ID")
    private Long id;

    private LocalDateTime orderDate;
    private OrderStatus status;

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderItem> orderItems = new HashSet<>();

    private Long customerAddressId;
    private Long customerId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Order(OrderStatus status, Set<OrderItem> orderItems, Long customerAddressId, Long customerId) {
        this(LocalDateTime.now(), status, orderItems, customerAddressId, customerId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Order(LocalDateTime orderDate, OrderStatus status, Set<OrderItem> orderItems, Long customerAddressId, Long customerId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
        this.customerAddressId = customerAddressId;
        this.customerId = customerId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

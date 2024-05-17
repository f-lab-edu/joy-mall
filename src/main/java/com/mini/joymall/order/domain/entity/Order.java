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


    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderItem> orderItems = new HashSet<>();

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderHistory> orderHistories = new HashSet<>();

    private Long customerAddressId;

    private LocalDateTime orderDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Order(Set<OrderItem> orderItems, Long customerAddressId) {
        this(orderItems, customerAddressId, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public Order(Set<OrderItem> orderItems, Long customerAddressId, LocalDateTime orderDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.orderItems = orderItems;
        this.customerAddressId = customerAddressId;
        this.orderDate = orderDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

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

    private Long customerAddressId;

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderItem> orderItems = new HashSet<>();

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderHistory> orderHistories = new HashSet<>();

    private LocalDateTime orderDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Order(Long customerAddressId, Set<OrderItem> orderItems, Set<OrderHistory> orderHistories) {
        this(customerAddressId, orderItems, orderHistories, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Order(Long customerAddressId, Set<OrderItem> orderItems, Set<OrderHistory> orderHistories, LocalDateTime orderDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.customerAddressId = customerAddressId;
        this.orderItems = orderItems;
        this.orderHistories = orderHistories;
        this.orderDate = orderDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Order ordered(Long customerAddressId, Set<OrderItem> orderItems) {
        Set<OrderHistory> orderHistories = new HashSet<>();
        orderHistories.add(OrderHistory.pending());
        return new Order(customerAddressId, orderItems, orderHistories);
    }

    public static int calculateOrderTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::calculateTotalPrice)
                .sum();
    }

    public static int getSumQuantity(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }
}

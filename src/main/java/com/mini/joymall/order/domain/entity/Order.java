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

    private LocalDateTime orderDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Order(Long customerAddressId, Set<OrderItem> orderItems) {
        this(customerAddressId, orderItems, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Order(Long customerAddressId, Set<OrderItem> orderItems, LocalDateTime orderDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.customerAddressId = customerAddressId;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static Order ordered(Long customerAddressId, Set<OrderItem> orderItems) {
        return new Order(customerAddressId, orderItems);
    }
}

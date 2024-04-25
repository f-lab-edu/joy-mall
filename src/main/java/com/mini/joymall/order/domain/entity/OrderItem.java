package com.mini.joymall.order.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("ORDER_ITEM")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class OrderItem {
    @Id
    @Column("ORDER_ITEM_ID")
    private Long id;

    private Long productId;

    private Long productOptionId;

    private Integer quantity;
    private Double pricePerItem;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public OrderItem(Long productId, Long productOptionId, Integer quantity, Double pricePerItem) {
        this(productId, productOptionId, quantity, pricePerItem, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public OrderItem(Long productId, Long productOptionId, Integer quantity, Double pricePerItem, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Double calculateTotalPrice() {
        return quantity * pricePerItem;
    }
}

package com.mini.joymall.order.dto;

import com.mini.joymall.order.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long salesProductId;
    private int quantity;

    public static OrderItemDTO from(OrderItem orderItem) {
        return OrderItemDTO
                .builder()
                .id(orderItem.getId())
                .salesProductId(orderItem.getSalesProductId())
                .quantity(orderItem.getQuantity())
                .build();
    }
}

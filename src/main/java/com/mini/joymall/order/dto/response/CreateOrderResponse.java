package com.mini.joymall.order.dto.response;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.order.domain.entity.Order;
import com.mini.joymall.order.domain.entity.OrderHistory;
import com.mini.joymall.order.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private Set<OrderItem> orderItems;
    private int totalPrice;
    private CustomerAddress customerAddress;

    public static CreateOrderResponse from(Order order, CustomerAddress customerAddress) {
        return CreateOrderResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .orderDate(order.getOrderDate())
                .orderItems(order.getOrderItems())
                .totalPrice(order.getOrderItems().stream()
                        .mapToInt(OrderItem::calculateTotalPrice)
                        .sum())
                .customerAddress(customerAddress)
                .build();
    }
}

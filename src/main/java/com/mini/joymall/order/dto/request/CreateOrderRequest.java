package com.mini.joymall.order.dto.request;

import com.mini.joymall.order.domain.entity.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull(message = "고객 ID를 입력해주세요.")
    private Long customerId;

    private List<CreateOrderItemRequest> orderItems;

    public Set<OrderItem> toOrderItems() {
        return orderItems.stream()
                .map(CreateOrderItemRequest::toEntity)
                .collect(Collectors.toSet());
    }
}

package com.mini.joymall.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    @NotNull(message = "고객 ID를 입력해주세요.")
    private Long customerId;

    private List<CreateOrderItemRequest> orderItems;
}

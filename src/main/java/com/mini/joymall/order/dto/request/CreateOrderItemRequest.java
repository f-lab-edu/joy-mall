package com.mini.joymall.order.dto.request;

import com.mini.joymall.order.domain.entity.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {
    @NotNull(message = "상품 옵션 ID를 입력해주세요.")
    private Long productOptionId;

    @NotNull(message = "상품 ID를 입력해주세요.")
    private Long productId;

    @NotNull(message = "선택 수량을 입력해주세요.")
    private Integer selectedQuantity;

    @NotNull(message = "가격을 입력해주세요.")
    private Integer price;

    public OrderItem toEntity() {
        return new OrderItem(productId, productOptionId, selectedQuantity, price);
    }
}

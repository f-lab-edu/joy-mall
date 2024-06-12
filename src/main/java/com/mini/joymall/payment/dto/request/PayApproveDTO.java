package com.mini.joymall.payment.dto.request;

import com.mini.joymall.payment.domain.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class PayApproveDTO {
    private String orderId;
    private PaymentMethod paymentMethod;

    public static PayApproveDTO from(Map<String, String> params) {
        if (params.get("orderId") == null || params.get("paymentMethod") == null) {
            throw new IllegalArgumentException("파라미터가 존재하지 않습니다.");
        }

        return new PayApproveDTO(params.get("orderId"), PaymentMethod.valueOf(params.get("paymentMethod")));
    }
}

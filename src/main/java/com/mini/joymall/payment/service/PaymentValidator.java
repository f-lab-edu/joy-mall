package com.mini.joymall.payment.service;

import com.mini.joymall.payment.domain.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PaymentValidator {

    public void validate(Payment payment) {
        validateDates(payment.getCreatedDate(), payment.getUpdatedDate());
        validateOrderId(payment.getOrderId());
    }

    private void validateDates(LocalDateTime createdDate, LocalDateTime updatedDate) {
        if (createdDate == null) {
            throw new IllegalArgumentException("생성일자는 null 일 수 없습니다.");
        }

        if (updatedDate == null) {
            throw new IllegalArgumentException("수정일자는 null 일 수 없습니다.");
        }
    }

    private void validateOrderId(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order Id는 null 일 수 없습니다.");
        }
    }

}

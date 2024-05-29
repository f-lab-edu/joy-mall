package com.mini.joymall.payment.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("PAYMENT_HISTORY")
public class PaymentHistory {
    @Id
    @Column("PAYMENT_HISTORY_ID")
    private Long id;
    private int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdDate;

    public PaymentHistory(int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this(amount, paymentMethod, paymentStatus, LocalDateTime.now());
    }

    @Builder
    public PaymentHistory(int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime createdDate) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.createdDate = createdDate;
    }

    public static PaymentHistory waiting(int amount, PaymentMethod paymentMethod) {
        return new PaymentHistory(amount, paymentMethod, PaymentStatus.WAITING);
    }

    public static PaymentHistory complete(int amount, PaymentMethod paymentMethod) {
        return new PaymentHistory(amount, paymentMethod, PaymentStatus.COMPLETED);
    }

    public static PaymentHistory failed(int amount, PaymentMethod paymentMethod) {
        return new PaymentHistory(amount, paymentMethod, PaymentStatus.FAILED);
    }
}

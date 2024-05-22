package com.mini.joymall.payment.domain.entity;

import com.mini.joymall.order.domain.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PAYMENT")
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @Column("PAYMENT_ID")
    private Long id;
    private int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Long orderId;

    public Payment(int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, Long orderId) {
        this(amount, paymentMethod, paymentStatus, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), orderId);
    }

    @Builder
    public Payment(int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime paymentDate, LocalDateTime createdDate, LocalDateTime updatedDate, Long orderId) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.orderId = orderId;
    }
}

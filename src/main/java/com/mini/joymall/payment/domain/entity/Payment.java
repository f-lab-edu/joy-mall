package com.mini.joymall.payment.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table("PAYMENT")
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @Column("PAYMENT_ID")
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Long orderId;

    @MappedCollection(idColumn = "PAYMENT_ID")
    private Set<PaymentHistory> paymentHistories;

    public Payment(Long orderId, Set<PaymentHistory> paymentHistories) {
        this(LocalDateTime.now(), LocalDateTime.now(), orderId, paymentHistories);
    }

    @Builder
    public Payment(LocalDateTime createdDate, LocalDateTime updatedDate, Long orderId, Set<PaymentHistory> paymentHistories) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.orderId = orderId;
        this.paymentHistories = paymentHistories;
    }

    public Payment waiting(int amount, PaymentMethod paymentMethod) {
        paymentHistories.add(PaymentHistory.waiting(amount, paymentMethod));
        return this;
    }

    public Payment requesting(int amount, PaymentMethod paymentMethod) {
        paymentHistories.add(PaymentHistory.requesting(amount, paymentMethod));
        return this;
    }

    public Payment complete(int amount, PaymentMethod paymentMethod) {
        paymentHistories.add(PaymentHistory.completed(amount, paymentMethod));
        return this;
    }

    public Payment failed(int amount, PaymentMethod paymentMethod) {
        paymentHistories.add(PaymentHistory.failed(amount, paymentMethod));
        return this;
    }
}

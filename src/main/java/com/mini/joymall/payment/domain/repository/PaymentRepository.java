package com.mini.joymall.payment.domain.repository;

import com.mini.joymall.payment.domain.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Optional<Payment> findByOrderId(String partnerOrderId);
}

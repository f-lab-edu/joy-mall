package com.mini.joymall.payment.domain.repository;

import com.mini.joymall.payment.domain.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}

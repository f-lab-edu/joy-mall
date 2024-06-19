package com.mini.joymall.payment.domain.repository;

import com.mini.joymall.payment.domain.entity.PgPayHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PgPayHistoryRepository extends CrudRepository<PgPayHistory, Long> {
    Optional<PgPayHistory> findByOrderId(String orderId);
}

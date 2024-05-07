package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    Optional<OrderHistory> findByOrderId(Long orderId);
}

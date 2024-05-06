package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
}

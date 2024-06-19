package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
}

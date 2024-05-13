package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

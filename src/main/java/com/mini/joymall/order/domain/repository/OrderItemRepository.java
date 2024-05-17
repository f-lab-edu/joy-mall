package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}

package com.mini.joymall.order.domain.repository;

import com.mini.joymall.order.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}

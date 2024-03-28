package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

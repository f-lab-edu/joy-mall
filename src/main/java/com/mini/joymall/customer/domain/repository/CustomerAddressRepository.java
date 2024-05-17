package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Long> {
    Optional<CustomerAddress> findByCustomerId(Long customerId);
}

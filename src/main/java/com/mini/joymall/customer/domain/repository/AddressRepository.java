package com.mini.joymall.customer.domain.repository;

import com.mini.joymall.customer.domain.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByCustomerId(Long customerId);
}

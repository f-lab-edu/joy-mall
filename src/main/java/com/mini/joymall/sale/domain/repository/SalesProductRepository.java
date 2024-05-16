package com.mini.joymall.sale.domain.repository;

import com.mini.joymall.sale.domain.entity.SalesProduct;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SalesProductRepository extends CrudRepository<SalesProduct, Long> {
    @Lock(LockMode.PESSIMISTIC_WRITE)
    Optional<SalesProduct> findById(Long id);
}

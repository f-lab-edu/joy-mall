package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameStartingWith(String keyword, Pageable pageable);

    @Lock(LockMode.PESSIMISTIC_WRITE)
    Optional<Product> findById(Long id);
}
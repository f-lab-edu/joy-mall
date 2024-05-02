package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.ProductOption;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductOptionRepository extends CrudRepository<ProductOption, Long> {

    @Lock(LockMode.PESSIMISTIC_WRITE)
    Optional<ProductOption> findById(Long id);

    @Modifying
    @Query("update PRODUCT_OPTION set STOCK_QUANTITY = :stockQuantity where OPTION_ID = :id")
    int updateStockQuantityById(Long id, Integer stockQuantity);
}

package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.ProductReviewSummary;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductReviewSummaryRepository extends CrudRepository<ProductReviewSummary, Long> {
    @Lock(LockMode.PESSIMISTIC_WRITE)
    @Query("select * from PRODUCT_REVIEW_SUMMARY where PRODUCT_ID = :productId")
    Optional<ProductReviewSummary> findByProductId(Long productId);
}

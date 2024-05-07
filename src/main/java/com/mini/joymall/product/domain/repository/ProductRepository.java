package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Modifying
    @Query("update PRODUCT set TOTAL_REVIEW_COUNT = :totalReviewCount where PRODUCT_ID = :id")
    int updateTotalReviewCount(Long id, int totalReviewCount);

    @Modifying
    @Query("update PRODUCT set AVERAGE_REVIEW_RATING = :averageReviewRating where PRODUCT_ID = :id")
    int updateAverageReviewRating(Long id, int averageReviewRating);
}
package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT P.* FROM PRODUCT P " +
            "JOIN (" +
            "    SELECT PRODUCT_ID FROM PRODUCT " +
            "    WHERE NAME LIKE CONCAT(:keyword, '%') " +
            "    ORDER BY PRODUCT_ID DESC " +
            "    LIMIT :limit OFFSET :offset" +
            ") P2 ON P.PRODUCT_ID = P2.PRODUCT_ID")
    List<ProductDTO> findProductsByNameStartsWith(@Param("keyword") String keyword,
                                                  @Param("limit") int limit,
                                                  @Param("offset") long offset);

    @Query("SELECT COUNT(*) FROM PRODUCT WHERE name LIKE CONCAT(:keyword, '%')")
    long countProductsByNameRange(@Param("keyword") String keyword);
}
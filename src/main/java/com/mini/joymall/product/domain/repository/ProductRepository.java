package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.dto.ProductWithReview;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = """
                    SELECT
                      p.*,
                      po.*,
                      r.*
                    FROM PRODUCT p
                    LEFT JOIN (
                      SELECT
                          PRODUCT_ID,
                          MIN(PRICE) AS OPTION_MIN_PRICE
                      FROM PRODUCT_OPTION
                      GROUP BY PRODUCT_ID
                    ) po ON p.PRODUCT_ID = po.PRODUCT_ID
                    LEFT JOIN (
                      SELECT
                          product_id,
                          COUNT(*) AS TOTAL_REVIEW_COUNT,
                          AVG(RATING) AS AVERAGE_REVIEW_RATING
                      FROM REVIEW
                      GROUP BY product_id
                    ) r ON p.PRODUCT_ID = r.PRODUCT_ID
                    WHERE UPPER(P.NAME) LIKE CONCAT('%', UPPER(:keyword), '%')
                    ORDER BY PRODUCT_ID DESC
                    LIMIT :pageSize
                    OFFSET :pageNumber
            """)
    List<ProductWithReview> findByNameContainingIgnoreCase(String keyword, int pageSize, int pageNumber);

    Long countByNameContainingIgnoreCase(String keyword);
}

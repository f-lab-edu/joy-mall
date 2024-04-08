package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ProductCategory {
    @Id
    @Column("PRODUCT_CATEGORY_ID")
    private Long id;
    private Long productId;
    private Long categoryId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ProductCategory(Long productId, Long categoryId) {
        this(null, productId, categoryId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public ProductCategory(Long productCategoryId, Long productId, Long categoryId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = productCategoryId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

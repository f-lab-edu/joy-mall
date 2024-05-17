package com.mini.joymall.product.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PRODUCT_CATEGORY")
@Getter
@ToString
@NoArgsConstructor
public class ProductCategory {
    @Id
    @Column("PRODUCT_CATEGORY_ID")
    private Long id;

    @Column("PRODUCT_ID")
    private Long productId;

    @Column("CATEGORY_ID")
    private Long categoryId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ProductCategory(Long productId, Long categoryId) {
        this(null, productId, categoryId, LocalDateTime.now(), LocalDateTime.now());
    }

    public ProductCategory(Long id, Long productId, Long categoryId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.productId = productId;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

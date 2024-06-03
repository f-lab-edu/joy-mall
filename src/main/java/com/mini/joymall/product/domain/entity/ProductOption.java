package com.mini.joymall.product.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PRODUCT_OPTION")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ProductOption {

    @Id
    @Column("PRODUCT_OPTION_ID")
    private Long id;

    @Column("PRODUCT_ID")
    private Long productId;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ProductOption(Long productId, String name) {
        this(productId, name, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public ProductOption(Long productId, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.productId = productId;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

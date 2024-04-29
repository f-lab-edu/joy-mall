package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@TypeAlias("CATEGORY")
@Getter
@ToString
@NoArgsConstructor
public class Category {
    @Id
    @Column("CATEGORY_ID")
    private Long id;
    private Long parentId;
    private Integer depth;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @MappedCollection(idColumn = "CATEGORY_ID")
    private Set<ProductCategory> productCategories = new HashSet<>();

    public Category(Long parentId, Integer depth, String name) {
        this(parentId, depth, name, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Category(Long parentId, Integer depth, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void addProduct(Product product) {
        productCategories.add(createProductCategory(product));
    }

    public ProductCategory createProductCategory(Product product) {
        return new ProductCategory(product.getId(), id);
    }
}
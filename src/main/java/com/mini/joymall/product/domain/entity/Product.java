package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table("PRODUCT")
@Getter
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @Column("PRODUCT_ID")
    private Long id;

    @Column("SELLER_ID")
    private Long sellerId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @MappedCollection(idColumn = "PRODUCT_ID")
    private Set<ProductCategory> productCategories = new HashSet<>();

    @MappedCollection(idColumn = "PRODUCT_ID")
    private Set<ProductOption> productOptions = new HashSet<>();

    public Product(Long sellerId, String name, String description, String imageUrl) {
        this(sellerId, name, description, imageUrl, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Product(Long sellerId, String name, String description, String imageUrl, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void addCategory(Category category) {
        productCategories.add(createProductCategory(category));
    }

    public ProductCategory createProductCategory(Category category) {
        return new ProductCategory(id, category.getId());
    }
}
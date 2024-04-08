package com.mini.joymall.product.domain.entity;

import com.mini.joymall.seller.domain.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Transient
    private Seller seller;

    public Product(String name, String description, Double price, Integer stockQuantity, String imageUrl) {
        this(null, null, name, description, price, stockQuantity, imageUrl, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Product(Long id, Long sellerId, String name, String description, Double price, Integer stockQuantity, String imageUrl, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
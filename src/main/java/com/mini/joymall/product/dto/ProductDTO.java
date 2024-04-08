package com.mini.joymall.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductCategory;
import com.mini.joymall.seller.domain.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Seller seller;
    private Set<ProductCategory> productCategories;


    @Builder
    public ProductDTO(Long id, String name, String description, Double price, Integer stockQuantity,
                      String imageUrl, LocalDateTime createdDate, LocalDateTime updatedDate, Seller seller, Set<ProductCategory> productCategories) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.seller = seller;
        this.productCategories = productCategories;
    }

    public static ProductDTO from(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
//                .seller(product.getSeller())
//                .productCategories(product.getProductCategories())
                .build();
    }
}

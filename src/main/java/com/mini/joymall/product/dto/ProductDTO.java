package com.mini.joymall.product.dto;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private int averageReviewRating;
    private int totalReviewCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int optionMinPrice;


    @Builder
    public ProductDTO(Long id, String name, String description, String imageUrl, int averageReviewRating, int totalReviewCount,
                      LocalDateTime createdDate, LocalDateTime updatedDate, int optionMinPrice) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.averageReviewRating = averageReviewRating;
        this.totalReviewCount = totalReviewCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.optionMinPrice = optionMinPrice;
    }

    public static ProductDTO from(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .name(this.getName())
                .description(this.getDescription())
                .imageUrl(this.getImageUrl())
                .createdDate(this.getCreatedDate())
                .updatedDate(this.getUpdatedDate())
                .build();
    }
}

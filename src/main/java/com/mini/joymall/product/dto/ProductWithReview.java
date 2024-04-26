package com.mini.joymall.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.review.domain.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProductWithReview {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Double optionMinPrice;

    private Double averageReviewRating;
    private Double totalReviewCount;

    @Builder
    public ProductWithReview(Long id, String name, String description, Double optionMinPrice, String imageUrl,
                             LocalDateTime createdDate, LocalDateTime updatedDate, Double averageReviewRating, Double totalReviewCount) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.optionMinPrice = optionMinPrice;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.averageReviewRating = averageReviewRating;
        this.totalReviewCount = totalReviewCount;
    }

    public static ProductWithReview from(Product product) {
        return ProductWithReview.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }
}

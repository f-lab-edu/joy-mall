package com.mini.joymall.product.dto.response;

import com.mini.joymall.product.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductReviewSummaryResponse {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Integer optionMinPrice;

    private Double averageReviewRating;
    private Integer totalReviewCount;

    @Builder
    public ProductReviewSummaryResponse(Long id, String name, String description, Integer optionMinPrice, String imageUrl,
                                        LocalDateTime createdDate, LocalDateTime updatedDate, Double averageReviewRating, Integer totalReviewCount) {
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

    public static ProductReviewSummaryResponse from(Product product) {
        return ProductReviewSummaryResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }
}

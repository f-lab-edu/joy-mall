package com.mini.joymall.product.dto.response;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.review.dto.ReviewSummaryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProductAndReviewResponse {
    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Set<ProductOption> productOptions = new HashSet<>();

    private Double averageReviewRating;
    private Integer totalReviewCount;

    @Builder
    public ProductAndReviewResponse(Long id, String name, String description, Integer price, Integer stockQuantity, String imageUrl,
                                    LocalDateTime createdDate, LocalDateTime updatedDate, Set<ProductOption> productOptions,
                                    Integer totalReviewCount, Double averageReviewRating) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.productOptions = productOptions;
        this.totalReviewCount = totalReviewCount;
        this.averageReviewRating = averageReviewRating;
    }

    public static ProductAndReviewResponse from(Product product) {
        return ProductAndReviewResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .productOptions(product.getProductOptions())
                .build();
    }

    public static ProductAndReviewResponse from(Product product, ReviewSummaryDTO reviewSummaryDTO) {
        return ProductAndReviewResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .totalReviewCount(reviewSummaryDTO.getTotalReviewCount())
                .averageReviewRating(reviewSummaryDTO.calculateAverageReviewRating())
                .productOptions(product.getProductOptions())
                .build();
    }
}

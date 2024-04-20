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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductWithReview {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Set<Review> reviews;
    private Double averageReviewRating;
    private Double totalReviewCount;

    @Builder
    public ProductWithReview(Long id, String name, String description, Double price, Integer stockQuantity,
                             String imageUrl, LocalDateTime createdDate, LocalDateTime updatedDate, Set<Review> reviews) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.averageReviewRating = calculateAverageReviewRating(reviews);
        this.totalReviewCount = getTotalReviewCount(reviews);
    }

    public static ProductWithReview from(Product product) {
        return ProductWithReview.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .reviews(product.getReviews())
                .build();
    }

    public double getTotalReviewCount(Set<Review> reviews) {
        return reviews.size();
    }

    public double calculateAverageReviewRating(Set<Review> reviews) {
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}

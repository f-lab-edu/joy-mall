package com.mini.joymall.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.review.dto.ReviewStatDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Set<ProductOption> productOptions = new HashSet<>();

    private Double averageReviewRating;
    private Double totalReviewCount;

    @Builder
    public ProductDTO(Long id, String name, String description, Double price, Integer stockQuantity, String imageUrl,
                      LocalDateTime createdDate, LocalDateTime updatedDate, Set<ProductOption> productOptions,
                      Double totalReviewCount, Double averageReviewRating) {
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

    public static ProductDTO from(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .productOptions(product.getProductOptions())
                .build();
    }

    public static ProductDTO from(Product product, ReviewStatDTO reviewStatDTO) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .totalReviewCount(reviewStatDTO.getTotalReviewCount())
                .averageReviewRating(reviewStatDTO.calculateAverageReviewRating())
                .productOptions(product.getProductOptions())
                .build();
    }
}

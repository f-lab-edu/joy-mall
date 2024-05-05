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
    private Integer averageReviewRating;
    private Integer totalReviewCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer optionMinPrice;


    @Builder
    public ProductDTO(Long id, String name, String description, String imageUrl, Integer averageReviewRating, Integer totalReviewCount,
                      LocalDateTime createdDate, LocalDateTime updatedDate, Integer optionMinPrice) {
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
                .averageReviewRating(product.getAverageReviewRating())
                .totalReviewCount(product.getTotalReviewCount())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .optionMinPrice(product.getProductOptions().stream()
                        .mapToInt(ProductOption::getPrice)
                        .min()
                        .orElse(0))
                .build();
    }
}

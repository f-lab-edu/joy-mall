package com.mini.joymall.review.dto;

import com.mini.joymall.review.domain.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long customerId;
    private Long productId;
    private String content;
    private Integer rating;

    @Builder
    public ReviewDTO(Long id, Long customerId, Long productId, String content, Integer rating) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.rating = rating;
    }

    public static ReviewDTO from(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .customerId(review.getCustomerId())
                .productId(review.getProductId())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}

package com.mini.joymall.review.dto.request;

import com.mini.joymall.review.domain.entity.Review;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateReviewRequest {
    @NotNull(message = "고객 ID를 입력해주세요.")
    private Long customerId;
    @NotNull(message = "상품 ID를 입력해주세요.")
    private Long productId;
    @NotNull(message = "내용을 입력해주세요.")
    private String content;
    @NotNull(message = "별점을 입력해주세요.")
    private Integer rating;

    @Builder
    public CreateReviewRequest(Long customerId, Long productId, String content, Integer rating) {
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.rating = rating;
    }

    public Review toEntity() {
        return Review.builder()
                .customerId(customerId)
                .productId(productId)
                .content(content)
                .rating(rating)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}

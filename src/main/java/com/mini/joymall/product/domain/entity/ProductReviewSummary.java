package com.mini.joymall.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PRODUCT_REVIEW_SUMMARY")
@Getter
@NoArgsConstructor
public class ProductReviewSummary {
    @Id
    @Column("PRODUCT_REVIEW_SUMMARY_ID")
    private Long id;
    private int averageReviewRating;
    private int totalReviewCount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;



    public ProductReviewSummary(int averageReviewRating, int totalReviewCount) {
        this(averageReviewRating, totalReviewCount, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public ProductReviewSummary(int averageReviewRating, int totalReviewCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.averageReviewRating = averageReviewRating;
        this.totalReviewCount = totalReviewCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    private int calculateTotalReviewCount() {
        return totalReviewCount + 1;
    }

    public void calculate(int newRating) {
        float sum = this.averageReviewRating * this.totalReviewCount + newRating;
        averageReviewRating = Math.round(sum / calculateTotalReviewCount());
        totalReviewCount = calculateTotalReviewCount();
    }
}

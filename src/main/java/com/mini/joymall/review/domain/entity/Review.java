package com.mini.joymall.review.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("REVIEW")
@Getter
@ToString
@NoArgsConstructor
public class Review {

    @Id
    @Column("REVIEW_ID")
    private Long id;

    @Column("CUSTOMER_ID")
    private Long customerId;

    @Column("PRODUCT_ID")
    private Long productId;
    private String content;
    private Integer rating;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Review(String content, Integer rating) {
        this(null, null, null, content, rating, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Review(Long id, Long customerId, Long productId, String content, Integer rating, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.rating = rating;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

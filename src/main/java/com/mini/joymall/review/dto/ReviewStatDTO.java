package com.mini.joymall.review.dto;

import com.mini.joymall.review.domain.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class ReviewStatDTO {
    private Set<Review> reviews;

    public ReviewStatDTO(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getTotalReviewCount() {
        return this.reviews.size();
    }

    public Double calculateAverageReviewRating() {
        return this.reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}

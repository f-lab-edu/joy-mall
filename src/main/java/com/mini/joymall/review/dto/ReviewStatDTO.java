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

    public double getTotalReviewCount() {
        return this.reviews.size();
    }

    public double calculateAverageReviewRating() {
        return this.reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}

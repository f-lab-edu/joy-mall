package com.mini.joymall.review.service;

import com.mini.joymall.review.domain.entity.Review;
import com.mini.joymall.review.domain.repository.ReviewRepository;
import com.mini.joymall.review.dto.ReviewSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewSummaryDTO statByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return new ReviewSummaryDTO(new HashSet<>(reviews));
    }
}

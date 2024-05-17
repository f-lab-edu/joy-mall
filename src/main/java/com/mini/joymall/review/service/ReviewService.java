package com.mini.joymall.review.service;

import com.mini.joymall.product.domain.entity.ProductReviewSummary;
import com.mini.joymall.review.domain.entity.Review;
import com.mini.joymall.product.domain.repository.ProductReviewSummaryRepository;
import com.mini.joymall.review.domain.repository.ReviewRepository;
import com.mini.joymall.review.dto.ReviewDTO;
import com.mini.joymall.review.dto.request.CreateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductReviewSummaryRepository summaryRepository;

    public ReviewDTO save(CreateReviewRequest createReviewRequest) {
        Review review = reviewRepository.save(createReviewRequest.toEntity());

        ProductReviewSummary productReviewSummary = summaryRepository.findByProductId(createReviewRequest.getProductId())
                .orElseThrow(NoSuchElementException::new);
        productReviewSummary.calculate(createReviewRequest.getRating());
        summaryRepository.save(productReviewSummary);

        return ReviewDTO.from(review);
    }
}

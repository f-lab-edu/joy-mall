package com.mini.joymall.review.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.review.domain.entity.Review;
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
    private final ProductRepository productRepository;

    public ReviewDTO save(CreateReviewRequest createReviewRequest) {
        Review review = reviewRepository.save(createReviewRequest.toEntity());
        long productId = createReviewRequest.getProductId();
        int newRating = createReviewRequest.getRating();

        Product product = productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new);
        productRepository.updateTotalReviewCount(productId, product.calculateTotalReviewCount());
        productRepository.updateAverageReviewRating(productId, product.calculateAverageReviewRating(newRating));
        return ReviewDTO.from(review);
    }
}

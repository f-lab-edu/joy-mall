package com.mini.joymall.review.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.review.dto.request.CreateReviewRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 리뷰를_저장시_리뷰평점과_총개수가_증가한다() {
        // given
        Product product = new Product(1L, "상품1", "상품1 설명1", "");
        Product saved = productRepository.save(product);
        CreateReviewRequest createReviewRequest1 = new CreateReviewRequest(1L, saved.getId(), "리뷰1", 3);
        CreateReviewRequest createReviewRequest2 = new CreateReviewRequest(1L, saved.getId(), "리뷰2", 2);
        CreateReviewRequest createReviewRequest3 = new CreateReviewRequest(1L, saved.getId(), "리뷰3", 5);
        CreateReviewRequest createReviewRequest4 = new CreateReviewRequest(1L, saved.getId(), "리뷰4", 1);
        CreateReviewRequest createReviewRequest5 = new CreateReviewRequest(1L, saved.getId(), "리뷰5", 4);
        CreateReviewRequest createReviewRequest6 = new CreateReviewRequest(1L, saved.getId(), "리뷰6", 5);

        // when
        reviewService.save(createReviewRequest1);
        Product findProduct1 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        reviewService.save(createReviewRequest2);
        Product findProduct2 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        reviewService.save(createReviewRequest3);
        Product findProduct3 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        reviewService.save(createReviewRequest4);
        Product findProduct4 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        reviewService.save(createReviewRequest5);
        Product findProduct5 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        reviewService.save(createReviewRequest6);
        Product findProduct6 = productRepository.findById(saved.getId()).orElseThrow(NoSuchElementException::new);

        // then 3 2 5 1 1
        assertThat(findProduct1.getAverageReviewRating()).isEqualTo(3);
        assertThat(findProduct1.getTotalReviewCount()).isEqualTo(1);

        assertThat(findProduct2.getAverageReviewRating()).isEqualTo(3);
        assertThat(findProduct2.getTotalReviewCount()).isEqualTo(2);

        assertThat(findProduct3.getAverageReviewRating()).isEqualTo(4);
        assertThat(findProduct3.getTotalReviewCount()).isEqualTo(3);

        assertThat(findProduct4.getAverageReviewRating()).isEqualTo(3);
        assertThat(findProduct4.getTotalReviewCount()).isEqualTo(4);

        assertThat(findProduct5.getAverageReviewRating()).isEqualTo(3);
        assertThat(findProduct5.getTotalReviewCount()).isEqualTo(5);

        assertThat(findProduct6.getAverageReviewRating()).isEqualTo(3);
        assertThat(findProduct6.getTotalReviewCount()).isEqualTo(6);
    }
}
package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductReviewSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductReviewSummaryRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_생성_후_리뷰통계_변화() {
        // given
        Product product1 = new Product(1L, "product1", "product1", "", LocalDateTime.now(), LocalDateTime.now());
        Product savedProduct1 = productRepository.save(product1);

        // when
        ProductReviewSummary productReviewSummary1 = savedProduct1.getProductReviewSummary();
        productReviewSummary1.calculate(5); // 5
        productReviewSummary1.calculate(4); // (5 * 1) + 4 / 2 = 5
        productReviewSummary1.calculate(3); // (5 * 2) + 3 / 3 = 4
        productReviewSummary1.calculate(2); // (4 * 3) + 2 / 4 = 4
        productReviewSummary1.calculate(1); // (4 * 4) + 1 / 5 = 3

        // then
        assertThat(productReviewSummary1.getAverageReviewRating()).isEqualTo(3);
        assertThat(productReviewSummary1.getTotalReviewCount()).isEqualTo(5);
    }
}
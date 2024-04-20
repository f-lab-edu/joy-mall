package com.mini.joymall.review.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.review.domain.entity.Review;
import com.mini.joymall.seller.domain.entity.Seller;
import com.mini.joymall.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
        sellerRepository.deleteAll();
        productRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    void 리뷰_등록해보고_상품리스트에서_조회_성공() {
        Customer customerD = new Customer("D@D.com", "1234", "customerD", "010-0000-1111");
        Customer savedCustomer = customerRepository.save(customerD);

        Seller sellerA = new Seller(null, "a@a.com", "1234", "a", "aStore", "010-1234-5678", LocalDateTime.now(), LocalDateTime.now());
        Seller savedSeller = sellerRepository.save(sellerA);

        Product product1 = new Product(null, savedSeller.getId(), "아이폰1", "아이폰1", 100.0, 100, "아이폰", LocalDateTime.now(), LocalDateTime.now());
        Product savedProduct1 = productRepository.save(product1);

        Review review = new Review(null, savedCustomer.getId(), savedProduct1.getId(), "리뷰입니다", 5, LocalDateTime.now(), LocalDateTime.now());
        reviewRepository.save(review);

        List<Seller> sellers = sellerRepository.findAll();

        Review savedReview = sellers.get(0)
                .getProducts().stream().toList()
                .get(0)
                .getReviews().stream().toList()
                .get(0);

        List<Product> products = productRepository.findAll();

        assertThat(savedReview.getContent()).isEqualTo("리뷰입니다");
        assertThat(savedReview.getRating()).isEqualTo(5);
    }
}
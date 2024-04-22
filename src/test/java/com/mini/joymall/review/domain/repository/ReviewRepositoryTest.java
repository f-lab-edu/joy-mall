package com.mini.joymall.review.domain.repository;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.customer.domain.repository.CustomerRepository;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductWithReview;
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
}
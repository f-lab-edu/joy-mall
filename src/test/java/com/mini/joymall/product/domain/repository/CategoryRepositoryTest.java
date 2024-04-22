package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.service.CategoryService;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        categoryRepository.deleteAll();

        Category category = categoryRepository.save(new Category(null, 0, "핸드폰/통신"));
        Long categoryId = category.getId();

        Category category1 = categoryRepository.save(new Category(categoryId, 1, "휴대폰기종"));
        categoryRepository.saveAll(Arrays.asList(
                new Category(categoryId, 1, "소재"),
                new Category(categoryId, 1, "색상")
        ));

        Category category2 = categoryRepository.save(new Category(category1.getId(), 2, "아이폰 14"));
        categoryRepository.saveAll(Arrays.asList(
                new Category(category1.getId(), 2, "아이폰 13")
        ));

        categoryRepository.saveAll(Arrays.asList(
                new Category(category2.getId(), 3, "256GB"),
                new Category(category2.getId(), 3, "128GB")
        ));
    }

    @Test
    void 카테고리_전체_조회() {
        assertThat(categoryRepository.findAll().size()).isEqualTo(8);
    }

    @Test
    void 카테고리_N_depth_조회() {
        List<Category> all = categoryRepository.findByDepth(1);
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void 카테고리와_함께_상품을_저장한다() {
        Product product1 = new Product(1L, "삼성 비스포크 냉장고", "가성비 최고", "이미지", LocalDateTime.now(), LocalDateTime.now());
        Product product2 = new Product(1L, "삼성 비스포크 세탁기", "가성비 최고", "이미지", LocalDateTime.now(), LocalDateTime.now());
        productRepository.save(product1);
        productRepository.save(product2);

        Category category = new Category(null, null, 0, "가전제품", LocalDateTime.now(), LocalDateTime.now());
        category.addProduct(product1);
        category.addProduct(product2);
        categoryRepository.save(category);

        List<Category> categories = categoryRepository.findAll();
        Set<ProductCategory> productCategories = categories.get(categories.size() - 1).getProductCategories();
        assertThat(productCategories.size()).isEqualTo(2);
    }
}
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
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

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
    void 카테고리_N_depth_조회() {
        List<Category> all = categoryRepository.findByDepth(1);
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void 카테고리와_함께_상품을_저장한다() {
        Product product1 = new Product(1L, "삼성 비스포크 냉장고", "가성비 최고", "이미지");
        Product product2 = new Product(1L, "삼성 비스포크 세탁기", "가성비 최고", "이미지");
        productRepository.save(product1);
        productRepository.save(product2);

        Category category = new Category(null, 0, "가전제품");
        category.addProduct(product1);
        category.addProduct(product2);
        Category savedCategory = categoryRepository.save(category);

        Category findCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);
        Set<ProductCategory> productCategories = findCategory.getProductCategories();
        assertThat(productCategories.size()).isEqualTo(2);
    }
}
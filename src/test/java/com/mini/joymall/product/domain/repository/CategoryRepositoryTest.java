package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.dto.CategoryChildrenResponse;
import com.mini.joymall.product.dto.CategoryDTO;
import com.mini.joymall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

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
}
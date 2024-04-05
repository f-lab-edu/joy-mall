package com.mini.joymall.product.domain.repository;

import com.mini.joymall.product.domain.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 카테고리_초기_데이터가_제대로_들어가는지_테스트() {
        assertThat(categoryRepository.findAll().size()).isEqualTo(22);
    }

    @Test
    void 카테고리_0depth_조회시_초기데이터_개수와_같다() {
        List<Category> all = categoryRepository.findByDepth(0);
        assertThat(all.size()).isEqualTo(22);
    }

    @Test
    void 카테고리_1depth_조회시_0개이다() {
        List<Category> all = categoryRepository.findByDepth(1);
        assertThat(all.size()).isEqualTo(0);
    }
}
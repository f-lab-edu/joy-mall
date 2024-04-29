package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CategoryServiceMockTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    @DisplayName("category depth 리스트 조회가 정상적이다.")
    void findByDepthOne() {
        // given
        List<Category> mockCategories = Arrays.asList(
                new Category(null, 0, "전자기기"),
                new Category(null, 0, "주방용품")
        );
        given(categoryRepository.findByDepth(0)).willReturn(mockCategories);

        // when
        List<CategoryDTO> categories = categoryService.findByDepth(0);

        // then
        assertThat(mockCategories.size()).isEqualTo(categories.size());
    }
}
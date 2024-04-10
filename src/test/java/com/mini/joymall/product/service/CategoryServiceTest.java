package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.CategoryChildrenResponse;
import com.mini.joymall.product.dto.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CategoryServiceTest {
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

    @Test
    @DisplayName("자식 노드 조회 테스트")
    void getCategoryChildren() {
        Long categoryId = 1L;
        Category category1 = new Category(categoryId, null, 0, "핸드폰/통신", null, null);
        Category category2 = new Category(2L, categoryId, 0, "휴대폰기종", null, null);
        Category category3 = new Category(3L, category2.getId(), 0, "아이폰14", null, null);
        Category category4 = new Category(4L, category3.getId(), 0, "256GB", null, null);
        Category category5 = new Category(5L, category3.getId(), 0, "128GB", null, null);

        given(categoryRepository.findById(categoryId)).willReturn(Optional.of(category1));
        given(categoryRepository.findByParentId(category1.getId())).willReturn(Collections.singletonList(category2));
        given(categoryRepository.findByParentId(category2.getId())).willReturn(Collections.singletonList(category3));
        given(categoryRepository.findByParentId(category3.getId())).willReturn(Arrays.asList(category4, category5));

        List<CategoryChildrenResponse> response1 = categoryService.getCategoryChildren(1L);

        assertThat(response1.size()).isEqualTo(1);
        assertThat(response1.get(0).getName()).isEqualTo("핸드폰/통신");

        List<CategoryChildrenResponse> response2 = response1.get(0).getChildren();
        assertThat(response2.size()).isEqualTo(1);
        assertThat(response2.get(0).getName()).isEqualTo("휴대폰기종");

        List<CategoryChildrenResponse> response3 = response2.get(0).getChildren();
        assertThat(response3.size()).isEqualTo(1);
        assertThat(response3.get(0).getName()).isEqualTo("아이폰14");

        List<CategoryChildrenResponse> response4 = response3.get(0).getChildren();
        assertThat(response4.size()).isEqualTo(2);
        assertThat(response4.get(0).getName()).isEqualTo("256GB");
        assertThat(response4.get(1).getName()).isEqualTo("128GB");
    }
}
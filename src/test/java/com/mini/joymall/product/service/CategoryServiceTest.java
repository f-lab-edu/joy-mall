package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.CategoryChildrenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("자식 노드 조회 테스트")
    void getCategoryChildren() {
        Category category1 = new Category(null, 0, "핸드폰/통신테스트");
        Category savedCategory1 = categoryRepository.save(category1);
        Category category2 = new Category(savedCategory1.getId(), 1, "휴대폰기종테스트");
        Category savedCategory2 = categoryRepository.save(category2);
        Category category3 = new Category(savedCategory2.getId(), 2, "아이폰14테스트");
        Category savedCategory3 = categoryRepository.save(category3);
        Category category4 = new Category(savedCategory3.getId(), 3, "256GB테스트");
        Category savedCategory4_1 = categoryRepository.save(category4);
        Category category5 = new Category(savedCategory3.getId(), 3, "128GB테스트");
        Category savedCategory4_2 = categoryRepository.save(category5);



        List<CategoryChildrenResponse> child1 = categoryService.getCategoryChildren(savedCategory1.getId());

        assertThat(child1.size()).isEqualTo(1);
        assertThat(child1.get(0).getName()).isEqualTo("핸드폰/통신테스트");

        List<CategoryChildrenResponse> child2 = child1.get(0).getChildren();
        assertThat(child2.size()).isEqualTo(1);
        assertThat(child2.get(0).getName()).isEqualTo("휴대폰기종테스트");

        List<CategoryChildrenResponse> child3 = child2.get(0).getChildren();
        assertThat(child3.size()).isEqualTo(1);
        assertThat(child3.get(0).getName()).isEqualTo("아이폰14테스트");

        List<CategoryChildrenResponse> child4 = child3.get(0).getChildren();
        assertThat(child4.size()).isEqualTo(2);
        assertThat(child4.get(0).getName()).isEqualTo("256GB테스트");
        assertThat(child4.get(1).getName()).isEqualTo("128GB테스트");
    }
}

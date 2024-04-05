package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findByDepth(int depth) {
        List<Category> categories = categoryRepository.findByDepth(depth);
        return categories.stream()
                .map(category -> CategoryDTO.builder()
                        .categoryId(category.getCategoryId())
                        .parentId(category.getParentId())
                        .depth(category.getDepth())
                        .name(category.getName())
                        .build()
                )
                .toList();
    }
}

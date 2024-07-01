package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.response.CategoryChildrenResponse;
import com.mini.joymall.product.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> findByDepth(int depth) {
        List<Category> categories = categoryRepository.findByDepth(depth);
        return categories.stream()
                .map(CategoryDTO::from)
                .toList();
    }

    public CategoryChildrenResponse getCategoryChildren(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        List<CategoryChildrenResponse> children = buildCategoryChildren(category.getId());
        return CategoryChildrenResponse.create(category, children);
    }

    public List<CategoryChildrenResponse> buildCategoryChildren(Long parentId) {
        return categoryRepository.findByParentId(parentId)
                .stream()
                .map(category -> new CategoryChildrenResponse(
                        category.getId(),
                        category.getParentId(),
                        category.getDepth(),
                        category.getName(),
                        buildCategoryChildren(category.getId())
                ))
                .toList();
    }
}

package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Category;
import com.mini.joymall.product.domain.repository.CategoryRepository;
import com.mini.joymall.product.dto.CategoryChildrenResponse;
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
                .map(CategoryDTO::from)
                .toList();
    }

    public List<CategoryChildrenResponse> getCategoryChildren(long id) {
        return categoryRepository.findById(id)
                .stream()
                .map(childCategory -> new CategoryChildrenResponse(
                        childCategory.getId(),
                        childCategory.getParentId(),
                        childCategory.getDepth(),
                        childCategory.getName(),
                        buildChildrenResponse(childCategory.getId())
                ))
                .toList();
    }

    public List<CategoryChildrenResponse> buildChildrenResponse(Long parentId) {
        return categoryRepository.findByParentId(parentId)
                .stream()
                .map(category -> new CategoryChildrenResponse(
                        category.getId(),
                        category.getParentId(),
                        category.getDepth(),
                        category.getName(),
                        buildChildrenResponse(category.getId())
                ))
                .toList();
    }
}

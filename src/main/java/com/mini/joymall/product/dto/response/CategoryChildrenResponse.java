package com.mini.joymall.product.dto.response;

import com.mini.joymall.product.domain.entity.Category;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryChildrenResponse {
    private Long categoryId;
    private Long parentId;
    private Integer depth;
    private String name;
    private List<CategoryChildrenResponse> children;

    @Builder
    public CategoryChildrenResponse(Long categoryId, Long parentId, Integer depth, String name, List<CategoryChildrenResponse> children) {
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
        this.children = children;
    }

    public static CategoryChildrenResponse create(Category category, List<CategoryChildrenResponse> children) {
        return CategoryChildrenResponse.builder()
                .categoryId(category.getId())
                .parentId(category.getParentId())
                .depth(category.getDepth())
                .name(category.getName())
                .children(children)
                .build();
    }
}
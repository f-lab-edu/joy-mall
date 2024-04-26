package com.mini.joymall.product.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.joymall.product.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CategoryDTO {
    private Long categoryId;
    private Long parentId;
    private Integer depth;
    private String name;

    @Builder
    public CategoryDTO(Long categoryId, Long parentId, Integer depth, String name) {
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
    }


    public static CategoryDTO from(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getId())
                .parentId(category.getParentId())
                .depth(category.getDepth())
                .name(category.getName())
                .build();
    }
}
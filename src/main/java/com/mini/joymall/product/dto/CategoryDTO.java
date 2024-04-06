package com.mini.joymall.product.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.joymall.product.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Long categoryId;
    private Long parentId;
    private Integer depth;
    private String name;
    private LocalDateTime createdDate;

    public CategoryDTO(Long categoryId, Long parentId, Integer depth, String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.depth = depth;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    private LocalDateTime updatedDate;
}
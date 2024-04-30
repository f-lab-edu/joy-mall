package com.mini.joymall.product.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryChildrenResponse {
    private Long categoryId;
    private Long parentId;
    private Integer depth;
    private String name;
    private List<CategoryChildrenResponse> children;
}
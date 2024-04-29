package com.mini.joymall.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductSearchDTO {
    private String keyword;
    private Integer pageSize = 10;
    private Integer pageNumber = 0;

    public ProductSearchDTO(String keyword, Integer pageSize, Integer pageNumber) {
        if (keyword != null) this.keyword = keyword;
        if (pageSize != null) this.pageSize = pageSize;
        if (pageNumber != null) this.pageNumber = pageNumber;
    }
}

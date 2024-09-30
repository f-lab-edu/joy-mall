package com.mini.joymall.product.dto.request;

import lombok.Getter;

@Getter
public class ProductSearchRequest {
    private String keyword;
    private Long lastProductId = Long.MAX_VALUE;
    private Integer pageSize = 10;

    public ProductSearchRequest(String keyword, Long lastProductId, Integer pageSize) {
        if (keyword != null) this.keyword = keyword;
        if (lastProductId != null) this.lastProductId = lastProductId;
        if (pageSize != null) this.pageSize = pageSize;
    }
}

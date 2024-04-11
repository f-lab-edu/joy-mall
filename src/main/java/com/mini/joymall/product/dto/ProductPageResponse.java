package com.mini.joymall.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPageResponse {
    @JsonProperty(value = "products", index = 0)
    private List<ProductDTO> productDTOS;
    private long totalElements;
    private long totalPages;
    private int pageNumber;
    private int pageSize;
    private boolean hasPrevious;
    private boolean hasNext;

    @Builder
    public ProductPageResponse(List<ProductDTO> productDTOS, long totalElements, long totalPages, int pageNumber, int pageSize, boolean hasPrevious, boolean hasNext) {
        this.productDTOS = productDTOS;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
    }
}

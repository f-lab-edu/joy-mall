package com.mini.joymall.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mini.joymall.product.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPageResponse {
    @JsonProperty(value = "products", index = 0)
    private List<ProductWithReview> productsWithReview;
    private long totalElements;
    private long totalPages;
    private int pageNumber;
    private int pageSize;
    private boolean hasPrevious;
    private boolean hasNext;

    @Builder
    public ProductPageResponse(List<ProductWithReview> productsWithReview, long totalElements, long totalPages, int pageNumber, int pageSize, boolean hasPrevious, boolean hasNext) {
        this.productsWithReview = productsWithReview;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
    }

    public static ProductPageResponse from(List<ProductWithReview> productsWithReview, Page<Product> products) {
        return ProductPageResponse.builder()
                .productsWithReview(productsWithReview)
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .pageNumber(products.getNumber())
                .pageSize(products.getSize())
                .hasPrevious(products.hasPrevious())
                .hasNext(products.hasNext())
                .build();
    }
}

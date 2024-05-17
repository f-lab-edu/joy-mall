package com.mini.joymall.product.dto.response;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.dto.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPageResponse {
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

    public static ProductPageResponse from(Page<Product> products) {
        return ProductPageResponse.builder()
                .productDTOS(products.stream()
                        .map(ProductDTO::from)
                        .toList())
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .pageNumber(products.getNumber())
                .pageSize(products.getSize())
                .hasPrevious(products.hasPrevious())
                .hasNext(products.hasNext())
                .build();
    }
}

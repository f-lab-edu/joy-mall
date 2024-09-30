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
    private long total;
    private boolean hasNext;
    private Long nextLastProductId;

    @Builder
    public ProductPageResponse(List<ProductDTO> productDTOS, long total, boolean hasNext, Long nextLastProductId) {
        this.productDTOS = productDTOS;
        this.total = total;
        this.hasNext = hasNext;
        this.nextLastProductId = nextLastProductId;
    }

    public static ProductPageResponse from(Page<ProductDTO> products) {
        return ProductPageResponse.builder()
                .productDTOS(products.getContent())
                .total(products.getTotalElements())
                .hasNext(products.hasNext())
                .nextLastProductId(builder().nextLastProductId)
                .build();
    }
}

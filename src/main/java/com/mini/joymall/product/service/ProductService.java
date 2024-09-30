package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductPageResponse search(String keyword, Pageable pageable) {
        List<ProductDTO> productDTOS = productRepository.findProductsByNameStartsWith(keyword, pageable.getPageSize(), pageable.getOffset());
        long total = productRepository.countProductsByNameRange(keyword);
        Page<ProductDTO> productPages = new PageImpl<>(productDTOS, pageable, total);
        return ProductPageResponse.from(productPages);
    }

    public ProductPageResponse search(String keyword, Long lastProductId, int pageSize) {
        List<ProductDTO> productDTOS = productRepository.findProductsByNameStartsWith(keyword, lastProductId, pageSize);
        long total = productRepository.countProductsByNameRange(keyword);

        boolean hasNext = productDTOS.size() == pageSize;
        Long nextLastProductId = productDTOS.get(productDTOS.size() - 1).getProductId();
        return new ProductPageResponse(productDTOS, total, hasNext, nextLastProductId);
    }
}

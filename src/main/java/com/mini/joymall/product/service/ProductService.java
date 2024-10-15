package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Cacheable(value = "products", key = "#keyword + '::' + #pageable.pageNumber + '::' + #pageable.pageSize")
    public ProductPageResponse search(String keyword, Pageable pageable) {
        List<ProductDTO> productDTOS = productRepository.findProductsByNameStartsWith(keyword, pageable.getPageSize(), pageable.getOffset());
        long total = productRepository.countProductsByNameRange(keyword);
        Page<ProductDTO> productPages = new PageImpl<>(productDTOS, pageable, total);
        return ProductPageResponse.from(productPages);
    }
}

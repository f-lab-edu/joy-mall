package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.ProductPageResponse;
import com.mini.joymall.product.dto.ProductWithReview;
import com.mini.joymall.review.dto.ReviewStatDTO;
import com.mini.joymall.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ReviewService reviewService;

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::from)
                .toList();
    }

    public ProductPageResponse search(String keyword, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int pageSize = pageable.getPageSize();
        List<ProductWithReview> productsWithReview = productRepository.findByNameContainingIgnoreCase(keyword, pageSize, offset);
        long totalCount = productRepository.countByNameContainingIgnoreCase(keyword);

        return ProductPageResponse.from(new PageImpl<>(productsWithReview, pageable, totalCount));
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        ReviewStatDTO reviewStatDTO = reviewService.statByProductId(id);

        return ProductDTO.from(product, reviewStatDTO);
    }
}

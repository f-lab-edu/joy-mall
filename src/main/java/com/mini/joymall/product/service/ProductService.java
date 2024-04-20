package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.ProductPageResponse;
import com.mini.joymall.product.dto.ProductWithReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::from)
                .toList();
    }

    public ProductPageResponse findByNameContainingIgnoreCase(String productName, Pageable pageable) {
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(productName, pageable);
        List<ProductWithReview> productsWithReview = products
                .stream()
                .map(ProductWithReview::from)
                .toList();

        return ProductPageResponse.from(productsWithReview, products);
    }
}

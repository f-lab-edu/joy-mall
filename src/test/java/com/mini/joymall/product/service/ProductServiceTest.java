package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductPageResponse;
import com.mini.joymall.product.dto.ProductWithReview;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void 대소문자를_무시하고_검색_성공() {
        // given
        List<Product> products = Arrays.asList(
                new Product(null, "iPHONE4", "아이폰입니다4", ""),
                new Product(null ,"iPHONE3", "아이폰입니다3", ""),
                new Product(null, "iPHONE2", "아이폰입니다2", ""),
                new Product(null, "iPHONE1", "아이폰입니다1", "")
        );

        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(0, 10, sort);

        List<ProductWithReview> list = products.stream()
                .map(ProductWithReview::from)
                .toList();

        Page<ProductWithReview> pages = new PageImpl<>(list, pageable, products.size());

        given(productRepository.findByNameContainingIgnoreCase("iphone", pageable.getPageSize(), pageable.getPageNumber())).willReturn(pages.getContent());

        // when
        ProductPageResponse productPageResponse = productService.search("iphone", pageable);
        List<ProductWithReview> productsWithReview = productPageResponse.getProductsWithReview();

        // then
        assertThat(products.size()).isEqualTo(productsWithReview.size());
        assertThat(products.get(1).getName()).isEqualTo("iPHONE3");
        assertThat(products.get(2).getDescription()).isEqualTo("아이폰입니다2");
    }

    @Test
    void 띄어쓰기를_포함한_검색_결과_없음() {
        // given
        List<ProductWithReview> mockProducts = Collections.emptyList();
        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<ProductWithReview> pages = new PageImpl<>(mockProducts, pageable, 0);

        // when
        given(productRepository.findByNameContainingIgnoreCase("ip hone", pageable.getPageSize(), pages.getNumber())).willReturn(pages.getContent());
        ProductPageResponse productPageResponse = productService.search("ip hone", pageable);
        List<ProductWithReview> productsWithReview = productPageResponse.getProductsWithReview();

        // then
        assertThat(productsWithReview).isEmpty();
    }
}
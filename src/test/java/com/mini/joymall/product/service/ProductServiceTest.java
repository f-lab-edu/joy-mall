package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.ProductPageResponse;
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
                new Product("iPHONE4", "아이폰입니다4", 1000.0, 100, ""),
                new Product("iPHONE3", "아이폰입니다3", 1000.0, 100, ""),
                new Product("iPHONE2", "아이폰입니다2", 1000.0, 100, ""),
                new Product("iPHONE1", "아이폰입니다1", 1000.0, 100, "")
        );

        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(1, 2, sort);

        Page<Product> pages = new PageImpl<>(products, pageable, products.size());

        given(productRepository.findByNameContainingIgnoreCase("iphone", pageable)).willReturn(pages);

        // when
        ProductPageResponse productPageResponse = productService.findByNameContainingIgnoreCase("iphone", pageable);
        List<ProductDTO> productDTOS = productPageResponse.getProductDTOS();

        // then
        assertThat(products.size()).isEqualTo(productDTOS.size());
        assertThat(products.get(1).getName()).isEqualTo("iPHONE3");
        assertThat(products.get(2).getDescription()).isEqualTo("아이폰입니다2");
    }

    @Test
    void 띄어쓰기를_포함한_검색_결과_없음() {
        // given
        List<Product> mockProducts = Collections.emptyList();
        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(1, 2, sort);
        Page<Product> pages = new PageImpl<>(mockProducts, pageable, mockProducts.size());

        // when
        given(productRepository.findByNameContainingIgnoreCase("ipho ne", pageable)).willReturn(pages);
        ProductPageResponse productPageResponse = productService.findByNameContainingIgnoreCase("ipho ne", pageable);
        List<ProductDTO> productDTOS = productPageResponse.getProductDTOS();
    }
}
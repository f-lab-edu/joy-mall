package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
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
        List<Product> mockProducts = Arrays.asList(
                new Product("iPHONE", "아이폰입니다", 1000.0, 100, ""),
                new Product("iPHONE2", "아이폰입니다2", 1000.0, 100, "")
        );

        given(productRepository.findByNameContaining("iphone")).willReturn(mockProducts);

        // when
        List<ProductDTO> productDTOs = productService.findByNameContaining("iphone");

        // then
        assertThat(mockProducts.size()).isEqualTo(productDTOs.size());
        assertThat(mockProducts.get(0).getName()).isEqualTo(mockProducts.get(0).getName());
        assertThat(mockProducts.get(1).getDescription()).isEqualTo(mockProducts.get(1).getDescription());
    }

    @Test
    void 띄어쓰기를_포함한_검색_결과_없음() {
        // given
        List<Product> mockProducts = Collections.emptyList();

        // when
        given(productRepository.findByNameContaining("ipho ne")).willReturn(mockProducts);

        // then
        List<ProductDTO> productDTOS = productService.findByNameContaining("ipho ne");
        assertThat(productDTOS).isEmpty();
    }
}
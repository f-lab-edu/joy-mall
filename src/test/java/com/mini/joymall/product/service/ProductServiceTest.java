package com.mini.joymall.product.service;

import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.entity.ProductOption;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.response.ProductPageResponse;
import com.mini.joymall.product.dto.ProductDTO;
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
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void 대소문자를_무시하고_검색_성공() {
        // given
        Product product1 = new Product(null, "iPHONE1", "아이폰입니다1", "");
        product1.addProductOption(new ProductOption(null, "옵션1", 1000, 100));

        Product product2 = new Product(null, "iPHONE2", "아이폰입니다2", "");
        product2.addProductOption(new ProductOption(null, "옵션1", 1000, 100));

        Product product3 = new Product(null, "iPHONE3", "아이폰입니다3", "");
        product3.addProductOption(new ProductOption(null, "옵션1", 1000, 100));

        Product product4 = new Product(null, "iPHONE4", "아이폰입니다4", "");
        product4.addProductOption(new ProductOption(null, "옵션1", 1000, 100));

        List<Product> products = Arrays.asList(
                product1, product2, product3, product4
        );


        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(0, 10, sort);

        Page<Product> pages = new PageImpl<>(products, pageable, products.size());


        // when
        given(productRepository.findByNameContainingIgnoreCase("iphone", pageable)).willReturn(pages);
        ProductPageResponse productPageResponse = productService.search("iphone", pageable);
        List<ProductDTO> productsWithReview = productPageResponse.getProductDTOS();

        // then
        assertThat(products.size()).isEqualTo(productsWithReview.size());
        assertThat(products.get(1).getName()).isEqualTo("iPHONE2");
        assertThat(products.get(2).getDescription()).isEqualTo("아이폰입니다3");
    }

    @Test
    void 띄어쓰기를_포함한_검색_결과_없음() {
        // given
        List<Product> mockProducts = Collections.emptyList();
        String sortBy = "DESC";
        Sort sort = Sort.by(Sort.Direction.fromString(sortBy), "id");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Product> pages = new PageImpl<>(mockProducts, pageable, 0);

        // when
        given(productRepository.findByNameContainingIgnoreCase("ip hone", pageable)).willReturn(pages);
        ProductPageResponse productPageResponse = productService.search("ip hone", pageable);
        List<ProductDTO> productsWithReview = productPageResponse.getProductDTOS();

        // then
        assertThat(productsWithReview).isEmpty();
    }
}
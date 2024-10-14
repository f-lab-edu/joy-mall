package com.mini.joymall.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.joymall.product.domain.entity.Product;
import com.mini.joymall.product.domain.repository.ProductRepository;
import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public ProductPageResponse search(String keyword, Pageable pageable) {
        String cacheKey = "searchProducts::" + keyword + "::" + pageable.getPageNumber() + "::" + pageable.getPageSize();
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            try {
                System.out.println("redis에 캐시된 정보를 가져온다.");
                System.out.println(cachedData);
                return objectMapper.readValue(cachedData, ProductPageResponse.class);
            } catch (Exception e) {
                System.err.println("캐시된 데이터 역직렬화 중 오류 발생: " + e.getMessage());
            }
        }

        List<ProductDTO> productDTOS = productRepository.findProductsByNameStartsWith(keyword, pageable.getPageSize(), pageable.getOffset());
        long total = productRepository.countProductsByNameRange(keyword);
        Page<ProductDTO> productPages = new PageImpl<>(productDTOS, pageable, total);
        ProductPageResponse response = ProductPageResponse.from(productPages);

        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            redisTemplate.opsForValue().set(cacheKey, jsonResponse);
            System.out.println("검색 결과를 Redis에 캐시한다.");
        } catch (Exception e) {
            System.err.println("겸색 결과 캐싱 중 오류 발생" + e.getMessage());
        }
        return response;
    }
}

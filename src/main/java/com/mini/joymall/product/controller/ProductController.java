package com.mini.joymall.product.controller;

import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.ProductPageResponse;
import com.mini.joymall.product.dto.ProductSearchDTO;
import com.mini.joymall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/products/search")
    public ResponseEntity<ProductPageResponse> search(@ModelAttribute ProductSearchDTO productSearchDTO) {
        Pageable pageable = PageRequest.of(productSearchDTO.getPageNumber(), productSearchDTO.getPageSize());
        ProductPageResponse productPageResponse = productService.search(productSearchDTO.getKeyword(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPageResponse);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }
}
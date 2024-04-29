package com.mini.joymall.product.controller;

import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.ProductPageResponse;
import com.mini.joymall.product.dto.ProductSearchDTO;
import com.mini.joymall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return OK(productService.findAll());
    }

    @GetMapping("/products/search")
    public ResponseEntity<ProductPageResponse> search(@ModelAttribute ProductSearchDTO productSearchDTO) {
        Pageable pageable = PageRequest.of(productSearchDTO.getPageNumber(), productSearchDTO.getPageSize());
        ProductPageResponse productPageResponse = productService.search(productSearchDTO.getKeyword(), pageable);
        return OK(productPageResponse);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return OK(productService.findById(id));
    }
}
package com.mini.joymall.product.controller;

import com.mini.joymall.product.dto.ProductDTO;
import com.mini.joymall.product.dto.response.ProductPageResponse;
import com.mini.joymall.product.dto.request.ProductSearchRequest;
import com.mini.joymall.product.service.ProductDataGenerator;
import com.mini.joymall.product.service.ProductService;
import com.mysql.cj.x.protobuf.Mysqlx;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mini.joymall.commons.ApiResponse.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDataGenerator productDataGenerator;

    @GetMapping("/products/search")
    public ResponseEntity<ProductPageResponse> search(@ModelAttribute ProductSearchRequest productSearchRequest) {
        ProductPageResponse productPageResponse = productService.search(productSearchRequest.getKeyword(), productSearchRequest.getLastProductId(), productSearchRequest.getPageSize());
        return OK(productPageResponse);
    }

    @GetMapping("/products/generateData")
    public void generateData() {
        productDataGenerator.generateProduct();
    }
}
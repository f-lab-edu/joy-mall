package com.mini.joymall.product.controller;

import com.mini.joymall.product.dto.CategoryDTO;
import com.mini.joymall.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    private ResponseEntity<List<CategoryDTO>> findByDepth(@RequestParam("depth") int depth) {
        List<CategoryDTO> categories = categoryService.findByDepth(depth);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
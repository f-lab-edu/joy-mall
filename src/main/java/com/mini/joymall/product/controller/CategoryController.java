package com.mini.joymall.product.controller;

import com.mini.joymall.product.dto.CategoryChildrenResponse;
import com.mini.joymall.product.dto.CategoryDTO;
import com.mini.joymall.product.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.mini.joymall.commons.ApiResponse.*;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    private ResponseEntity<List<CategoryDTO>> findByDepth(@RequestParam("depth") int depth) {
        return OK(categoryService.findByDepth(depth));
    }

    @GetMapping("/categories/children")
    private ResponseEntity<List<CategoryChildrenResponse>> getCategoryChildren(@RequestParam("id") long id) {
        return OK(categoryService.getCategoryChildren(id));
    }
}

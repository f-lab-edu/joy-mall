package com.mini.joymall.product.domain.entity;

import org.springframework.data.annotation.Id;


public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
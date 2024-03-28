package com.mini.joymall.review.domain.entity;

import com.mini.joymall.customer.domain.entity.Customer;
import com.mini.joymall.product.domain.entity.Product;

public class Review {
    private Long id;
    private String content;
    private Customer customer;
    private Product product;
}

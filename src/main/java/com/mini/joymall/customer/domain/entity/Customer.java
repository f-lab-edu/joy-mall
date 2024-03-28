package com.mini.joymall.customer.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

public class Customer {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}

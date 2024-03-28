package com.mini.joymall.customer.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CUSTOMER")
public class Customer {
    @Id
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public Customer(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

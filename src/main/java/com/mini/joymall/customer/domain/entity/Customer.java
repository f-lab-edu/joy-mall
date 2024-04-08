package com.mini.joymall.customer.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("CUSTOMER")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Customer {
    @Id
    @Column("CUSTOMER_ID")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Customer(String email, String password, String name, String phoneNumber) {
        this(null, email, password, name, phoneNumber, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Customer(Long id, String email, String password, String name, String phoneNumber, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

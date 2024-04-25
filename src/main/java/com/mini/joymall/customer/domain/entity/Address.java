package com.mini.joymall.customer.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("ADDRESS")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Address {
    @Id
    @Column("ADDRESS_ID")
    private Long id;

    @Column("CUSTOMER_ID")
    private Long customerId;

    private String receiptName;
    private String receiptPhoneNumber;
    private String region;
    private String city;
    private String town;
    private String street;
    private String zipCode;
    private String detail;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Address(Long customerId, String receiptName, String receiptPhoneNumber, String region, String city, String town, String street, String zipCode, String detail) {
        this(customerId, receiptName, receiptPhoneNumber, region, city, town, street, zipCode, detail, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public Address(Long customerId, String receiptName, String receiptPhoneNumber, String region, String city, String town, String street, String zipCode, String detail, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.customerId = customerId;
        this.receiptName = receiptName;
        this.receiptPhoneNumber = receiptPhoneNumber;
        this.region = region;
        this.city = city;
        this.town = town;
        this.street = street;
        this.zipCode = zipCode;
        this.detail = detail;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

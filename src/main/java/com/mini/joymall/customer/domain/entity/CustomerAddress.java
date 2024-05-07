package com.mini.joymall.customer.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("CUSTOMER_ADDRESS")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerAddress {
    @Id
    @Column("CUSTOMER_ADDRESS_ID")
    private long id;

    @Column("CUSTOMER_ID")
    private long customerId;

    private String receiptName;
    private String receiptPhoneNumber;
    
    @Embedded.Nullable
    private Location location;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CustomerAddress(long customerId, String receiptName, String receiptPhoneNumber, Location location) {
        this(customerId, receiptName, receiptPhoneNumber, location, LocalDateTime.now(), LocalDateTime.now());
    }

    @Builder
    public CustomerAddress(long customerId, String receiptName, String receiptPhoneNumber, Location location, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.customerId = customerId;
        this.receiptName = receiptName;
        this.receiptPhoneNumber = receiptPhoneNumber;
        this.location = location;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

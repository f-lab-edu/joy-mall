package com.mini.joymall.customer.dto;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.entity.Location;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomerAddressDTO {
    private Long id;
    private Long customerId;
    private String receiptName;
    private String receiptPhoneNumber;
    private Location location;
    private LocalDateTime createdDate;

    public static CustomerAddressDTO from(CustomerAddress customerAddress) {
        return CustomerAddressDTO.builder()
                .id(customerAddress.getId())
                .customerId(customerAddress.getCustomerId())
                .receiptName(customerAddress.getReceiptName())
                .receiptPhoneNumber(customerAddress.getReceiptPhoneNumber())
                .location(customerAddress.getLocation())
                .createdDate(customerAddress.getCreatedDate())
                .build();
    }
}

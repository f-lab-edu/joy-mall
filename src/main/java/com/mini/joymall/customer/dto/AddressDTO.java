package com.mini.joymall.customer.dto;

import com.mini.joymall.customer.domain.entity.Address;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AddressDTO {
    private Long id;
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

    public static AddressDTO from(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .customerId(address.getCustomerId())
                .receiptName(address.getReceiptName())
                .receiptPhoneNumber(address.getReceiptPhoneNumber())
                .region(address.getRegion())
                .city(address.getCity())
                .town(address.getTown())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .detail(address.getDetail())
                .createdDate(address.getCreatedDate())
                .build();
    }
}

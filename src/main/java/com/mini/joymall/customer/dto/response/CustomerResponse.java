package com.mini.joymall.customer.dto.response;

import com.mini.joymall.customer.domain.entity.Customer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class CustomerResponse {
    private Long id;

    private String email;

    private String name;

    private String phoneNumber;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .createdDate(customer.getCreatedDate())
                .updatedDate(customer.getUpdatedDate())
                .build();
    }
}

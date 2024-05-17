package com.mini.joymall.customer.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String region;
    private String city;
    private String town;
    private String street;
    private String zipCode;
    private String detail;
}

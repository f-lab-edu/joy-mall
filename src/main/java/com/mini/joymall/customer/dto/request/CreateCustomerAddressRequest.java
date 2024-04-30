package com.mini.joymall.customer.dto.request;

import com.mini.joymall.customer.domain.entity.CustomerAddress;
import com.mini.joymall.customer.domain.entity.Location;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerAddressRequest {
    @NotNull(message = "고객 ID를 입력해주세요.")
    private Long customerId;

    @NotEmpty(message = "수령인 이름을 입력해주세요.")
    private String receiptName;

    @NotEmpty(message = "수령인 번호를 입력해주세요.")
    private String receiptPhoneNumber;

    @NotEmpty(message = "지역을 입력해주세요.")
    private String region;

    @NotEmpty(message = "시/도를 입력해주세요.")
    private String city;

    @NotEmpty(message = "구/군을 입력해주세요.")
    private String town;

    @NotEmpty(message = "도로명을 입력해주세요.")
    private String street;

    @NotEmpty(message = "우편 번호를 입력해주세요.")
    private String zipCode;

    @NotEmpty(message = "상세 주소를 입력해주세요.")
    private String detail;

    public CustomerAddress toEntity() {
        return CustomerAddress.builder()
                .customerId(customerId)
                .receiptName(receiptName)
                .receiptPhoneNumber(receiptPhoneNumber)
                .location(Location.builder()
                        .region(region)
                        .city(city)
                        .town(town)
                        .street(street)
                        .zipCode(zipCode)
                        .detail(detail)
                        .build())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}

package com.mini.joymall.customer.dto;

import com.mini.joymall.customer.domain.entity.Customer;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "휴대폰 번호는 필수입니다.")
    private String phoneNumber;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public Customer toEntity() {
        return Customer.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}

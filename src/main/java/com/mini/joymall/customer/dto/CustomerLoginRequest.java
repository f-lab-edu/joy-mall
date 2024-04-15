package com.mini.joymall.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CustomerLoginRequest {
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
}

package com.pet.moduleregister.adapters.in.web.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginParams {
    @NotBlank(message = "userCode is required")
    private String userCode;
    @NotBlank(message = "password is required")
    private String password;
}

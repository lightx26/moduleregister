package com.pet.moduleregister.adapters.in.web.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginParams {
    @NotBlank(message = "userId is required")
    private String userId;
    @NotBlank(message = "password is required")
    private String password;
}

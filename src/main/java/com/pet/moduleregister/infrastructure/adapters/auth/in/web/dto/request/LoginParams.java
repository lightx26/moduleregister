package com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginParams {
    @NotBlank(message = "userCode is required")
    private String userCode;
    @NotBlank(message = "password is required")
    private String password;
}

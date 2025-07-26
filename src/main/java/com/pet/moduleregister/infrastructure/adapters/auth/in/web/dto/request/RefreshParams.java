package com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshParams {
    @NotBlank(message = "refreshToken is required")
    private String refreshToken;
}

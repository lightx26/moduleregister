package com.pet.moduleregister.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultDTO {
    private String accessToken;
    private String refreshToken;
}

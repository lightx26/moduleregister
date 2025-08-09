package com.pet.moduleregister.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userCode;
    private String password;
}

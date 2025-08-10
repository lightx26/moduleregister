package com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RegisterSuccessResponse {
    private String moduleClassCode;
    private Instant registeredAt;
}

package com.pet.moduleregister.application.moduleClassStudent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RegisteredModuleClass {
    private String moduleClassCode;
    private Instant registeredAt;
}

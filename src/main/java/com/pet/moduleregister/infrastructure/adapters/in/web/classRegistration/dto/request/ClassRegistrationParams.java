package com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRegistrationParams {
    @NotBlank(message = "Module class code must not be blank")
    private String moduleClassCode;
}

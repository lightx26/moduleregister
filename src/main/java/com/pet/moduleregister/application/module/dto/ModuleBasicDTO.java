package com.pet.moduleregister.application.module.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ModuleBasicDTO {
    private Long moduleId;
    private String moduleCode;
    private String moduleName;
    private BigDecimal numberOfCredits;
    private boolean isCompulsory;
}
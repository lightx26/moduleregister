package com.pet.moduleregister.application.moduleClass.dto.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ModuleInProgram {
    private Long moduleId;
    private String moduleCode;
    private String moduleName;
    private BigDecimal numberOfCredits;
    private boolean isCompulsory;
}

package com.pet.moduleregister.domain.electiveModule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElectiveModule {
    private Long electiveModuleId;
    private Long moduleId;
    private Long electiveGroupId;
}

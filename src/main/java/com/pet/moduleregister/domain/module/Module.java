package com.pet.moduleregister.domain.module;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Module {
    private Long moduleId;
    private String moduleCode;
    private String moduleName;
    private int numberOfCredits;
    private boolean isCompulsory;
    private Long responsibleFaculty;

    private Instant createdAt;
    private Instant updatedAt;
}

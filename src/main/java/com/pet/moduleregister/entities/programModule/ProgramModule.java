package com.pet.moduleregister.entities.programModule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ProgramModule {
    private Long programModuleId;
    private Long programId;
    // Compulsory module only
    private Long moduleId;
    // Elective module only
    private Long electiveGroup;
    private int semesterOrder;

    private Instant createdAt;
    private Instant updatedAt;
}

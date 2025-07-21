package com.pet.moduleregister.domain.moduleClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ModuleClass {
    private Long moduleClassId;

    private String moduleClassCode;
    private int maxParticipants;

    private Long moduleId;
    private Long lecturerId;
    private Long semesterId;

    private LocalDate startDate;
    private LocalDate endDate;

    private Instant createdAt;
    private Instant updatedAt;
}

package com.pet.moduleregister.entities.program;

import com.pet.moduleregister.entities.program.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Program {
    private Long programId;
    private String programCode;
    private String programName;
    private int numberOfSemesters;
    private int totalCredits;
    private int compulsoryCredits;
    private Long responsibleFaculty;
    private Language language;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    private Instant createdAt;
    private Instant updatedAt;
}

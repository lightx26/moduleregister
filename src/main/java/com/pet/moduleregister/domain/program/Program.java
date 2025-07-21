package com.pet.moduleregister.domain.program;

import com.pet.moduleregister.domain.program.enums.Language;
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
    private int numberOfCredits;
    private Long responsibleFaculty;
    private Language language;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    private Instant createdAt;
    private Instant updatedAt;
}

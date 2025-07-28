package com.pet.moduleregister.entities.studentCohort;

import com.pet.moduleregister.entities.studentCohort.enums.DegreeProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class StudentCohort {
    private Long studentCohortId;
    private String cohortName;
    private int entryYear;
    private DegreeProgram degreeProgram;

    private Instant createdAt;
    private Instant updatedAt;
}

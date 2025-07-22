package com.pet.moduleregister.domain.semester;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Semester {
    private Long semesterId;
    private String semesterName;
    private int orderInYear;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    private Instant createdAt;
    private Instant updatedAt;
}

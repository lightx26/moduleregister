package com.pet.moduleregister.application.moduleClass.dto.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OpeningClass {
    private Long moduleClassId;
    private String moduleClassCode;
    private String moduleName;
    private BigDecimal numberOfCredits;
    private Lecturer lecturer;
    private List<Schedule> schedules;

    private int maxParticipants;
    private int currentParticipants;
}

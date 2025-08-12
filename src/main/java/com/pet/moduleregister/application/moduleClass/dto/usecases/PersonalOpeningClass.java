package com.pet.moduleregister.application.moduleClass.dto.usecases;

import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PersonalOpeningClass {
    private Long moduleClassId;
    private String moduleClassCode;
    private Long moduleId;
    private String moduleName;
    private BigDecimal numberOfCredits;
    private Lecturer lecturer;
    private List<Schedule> schedules;
    private LearnStatus learnStatus;

    private int maxParticipants;
    private int currentParticipants;
}

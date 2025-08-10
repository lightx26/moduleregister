package com.pet.moduleregister.application.moduleClassStudent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class ClassSchedule {
    private Long moduleClassId;
    private DayOfWeek dayOfWeek;
    private int startPeriod;
    private int endPeriod;
}

package com.pet.moduleregister.application.moduleClass.dto.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long moduleClassId;
    private DayOfWeek dayOfWeek;
    private int startPeriod;
    private int endPeriod;
    private String room;
}

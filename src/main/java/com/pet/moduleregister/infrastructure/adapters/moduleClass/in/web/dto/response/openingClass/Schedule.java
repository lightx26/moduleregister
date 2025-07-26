package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class Schedule {
    private DayOfWeek dayOfWeek;
    private int startPeriod;
    private int endPeriod;
    private String room;
}

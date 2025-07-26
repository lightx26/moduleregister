package com.pet.moduleregister.domain.classSchedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.Instant;

@Getter
@AllArgsConstructor
public class ClassSchedule {
    private Long classScheduleId;
    private Long moduleClassId;
    private DayOfWeek dayOfWeek;
    private int startPeriod;    // From 1 to 10
    private int endPeriod;      // From 1 to 10
    private String room;

    private Instant createdAt;
    private Instant updatedAt;
}

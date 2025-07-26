package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.facade;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.Lecturer;
import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.Schedule;

import java.util.List;

public class ResponseMapper {
    public static OpeningClass mapToOpeningClass(
            com.pet.moduleregister.application.moduleClass.dto.OpeningClass openingClass) {
        Lecturer lecturer = new Lecturer(
                openingClass.getLecturer().getLecturerId(),
                openingClass.getLecturer().getFirstName(),
                openingClass.getLecturer().getLastName()
        );
        List<Schedule> schedules = openingClass.getSchedules().stream().map(
                schedule -> new Schedule(
                        schedule.getDayOfWeek(),
                        schedule.getStartPeriod(),
                        schedule.getEndPeriod(),
                        schedule.getRoom()
                )
        ).toList();

        return new OpeningClass(
                openingClass.getModuleClassId(),
                openingClass.getModuleClassCode(),
                openingClass.getModuleName(),
                openingClass.getNumberOfCredits(),
                lecturer,
                schedules,
                openingClass.getMaxParticipants(),
                openingClass.getCurrentParticipants()
        );
    }
}

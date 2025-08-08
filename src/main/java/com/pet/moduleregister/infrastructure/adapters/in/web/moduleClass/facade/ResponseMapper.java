package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.facade;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.*;

import java.util.List;

public class ResponseMapper {
    public static OpeningClass mapToOpeningClass(
            com.pet.moduleregister.application.moduleClass.dto.OpeningClass openingClass) {
        Lecturer lecturer = new Lecturer(
                openingClass.getLecturer().getLecturerId().toString(),
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
                openingClass.getModuleClassCode(),
                openingClass.getModuleName(),
                openingClass.getNumberOfCredits(),
                lecturer,
                schedules,
                openingClass.getMaxParticipants(),
                openingClass.getCurrentParticipants()
        );
    }

    public static OpeningClassDetailsResponse mapToOpeningClassDetailsResponse(OpeningClassDetails openingClassDetails) {
        Lecturer lecturer = new Lecturer(
                openingClassDetails.getLecturer().getLecturerId().toString(),
                openingClassDetails.getLecturer().getFirstName(),
                openingClassDetails.getLecturer().getLastName()
        );
        List<Schedule> schedules = openingClassDetails.getSchedules().stream().map(
                schedule -> new Schedule(
                        schedule.getDayOfWeek(),
                        schedule.getStartPeriod(),
                        schedule.getEndPeriod(),
                        schedule.getRoom()
                )
        ).toList();
        List<ClassParticipant> participants = openingClassDetails.getParticipants().stream()
                .map(participant -> new ClassParticipant(
                        participant.getStudentCode(),
                        participant.getStudentFirstName(),
                        participant.getStudentLastName(),
                        participant.getGroupName(),
                        participant.getProgramCode(),
                        participant.getProgramName()
                )).toList();
        List<ClassParticipant> waitingList = openingClassDetails.getWaitingList().stream()
                .map(participant -> new ClassParticipant(
                        participant.getStudentCode(),
                        participant.getStudentFirstName(),
                        participant.getStudentLastName(),
                        participant.getGroupName(),
                        participant.getProgramCode(),
                        participant.getProgramName()
                )).toList();

        return new OpeningClassDetailsResponse(
                openingClassDetails.getModuleClassCode(),
                openingClassDetails.getModuleName(),
                openingClassDetails.getNumberOfCredits(),
                lecturer,
                schedules,
                openingClassDetails.getMaxParticipants(),
                openingClassDetails.getCurrentParticipants(),
                participants,
                waitingList
        );
    }
}

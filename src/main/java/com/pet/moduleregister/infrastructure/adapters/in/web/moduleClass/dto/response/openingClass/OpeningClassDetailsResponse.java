package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OpeningClassDetailsResponse {
    private String moduleClassCode;
    private String moduleName;
    private int numberOfCredits;
    private Lecturer lecturer;
    private List<Schedule> schedules;

    private int maxParticipants;
    private int currentParticipants;

    private List<ClassParticipant> participants;
    private List<ClassParticipant> waitingList;
}

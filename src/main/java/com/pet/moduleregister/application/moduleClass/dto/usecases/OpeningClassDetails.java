package com.pet.moduleregister.application.moduleClass.dto.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OpeningClassDetails {
    private Long moduleClassId;
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

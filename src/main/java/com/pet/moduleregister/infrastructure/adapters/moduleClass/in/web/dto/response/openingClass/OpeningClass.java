package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OpeningClass {
    private Long moduleClassId;
    private String moduleClassCode;
    private String moduleName;
    private int numberOfCredits;
    private Lecturer lecturer;
    private List<Schedule> schedules;

    private int maxParticipants;
    private int currentParticipants;
}

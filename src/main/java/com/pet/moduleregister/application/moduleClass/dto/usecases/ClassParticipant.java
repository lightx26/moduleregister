package com.pet.moduleregister.application.moduleClass.dto.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassParticipant {
    private Long studentId;
    private String studentCode;
    private String studentFirstName;
    private String studentLastName;
    private String groupName;

    private String programCode;
    private String programName;
}

package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassParticipant {
    private String studentCode;
    private String studentFirstName;
    private String studentLastName;

    private String groupName;

    private String programCode;
    private String programName;
}

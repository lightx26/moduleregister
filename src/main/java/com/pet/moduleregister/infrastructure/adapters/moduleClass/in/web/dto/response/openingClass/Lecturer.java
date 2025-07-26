package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Lecturer {
    private Long lecturerId;
    private String firstName;
    private String lastName;
}

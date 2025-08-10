package com.pet.moduleregister.application.moduleClassStudent.dto;

import com.pet.moduleregister.entities.registrationPeriod.enums.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RegistrationPeriod {
    private Long semesterId;

    private Instant startTime;
    private Instant endTime;

    private RegistrationType type;
}

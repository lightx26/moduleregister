package com.pet.moduleregister.application.moduleClass.dto;

import com.pet.moduleregister.entities.registrationPeriod.enums.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationPeriod {
    private Long semesterId;

    private Instant startTime;
    private Instant endTime;

    private RegistrationType type;
}

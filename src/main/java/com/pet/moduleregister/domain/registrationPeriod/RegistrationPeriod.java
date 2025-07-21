package com.pet.moduleregister.domain.registrationPeriod;

import com.pet.moduleregister.domain.registrationPeriod.enums.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RegistrationPeriod {
    private Long registrationPeriodId;
    private Long semesterId;

    private Instant startTime;
    private Instant endTime;

    private RegistrationType type;

    private Instant createdAt;
    private Instant updatedAt;
}

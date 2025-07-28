package com.pet.moduleregister.entities.registrationPeriod;

import com.pet.moduleregister.entities.registrationPeriod.enums.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
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

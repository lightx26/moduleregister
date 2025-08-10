package com.pet.moduleregister.application.registrationPeriod.ports.in.query;

import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;

import java.util.Optional;

public interface GetCurrentPeriodQuery {
    Optional<RegistrationPeriod> getCurrentPeriod();
}

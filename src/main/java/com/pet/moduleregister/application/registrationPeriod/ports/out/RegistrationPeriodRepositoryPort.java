package com.pet.moduleregister.application.registrationPeriod.ports.out;

import com.pet.moduleregister.domain.registrationPeriod.RegistrationPeriod;

import java.time.Instant;
import java.util.Optional;

public interface RegistrationPeriodRepositoryPort {
    Optional<RegistrationPeriod> getPeriodByDate(Instant date);
}

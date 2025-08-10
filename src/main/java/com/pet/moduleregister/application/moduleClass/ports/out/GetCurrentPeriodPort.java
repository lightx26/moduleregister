package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.usecases.RegistrationPeriod;

import java.util.Optional;

public interface GetCurrentPeriodPort {
    Optional<RegistrationPeriod> getCurrentPeriod();
}

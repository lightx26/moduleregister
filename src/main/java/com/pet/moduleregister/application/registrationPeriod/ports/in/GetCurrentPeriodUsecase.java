package com.pet.moduleregister.application.registrationPeriod.ports.in;

import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;

public interface GetCurrentPeriodUsecase {
    RegistrationPeriod getCurrentPeriod();
}

package com.pet.moduleregister.application.registrationPeriod.ports.in;

import com.pet.moduleregister.domain.registrationPeriod.RegistrationPeriod;

public interface GetCurrentPeriodUsecase {
    RegistrationPeriod getCurrentPeriod();
}

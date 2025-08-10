package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.usecases.RegistrationPeriod;

public interface GetCurrentPeriodPort {
    RegistrationPeriod getCurrentPeriod();
}

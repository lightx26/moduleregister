package com.pet.moduleregister.application.registrationPeriod.ports.in.query;

import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;

public interface GetCurrentPeriod {
    RegistrationPeriod getCurrentPeriod();
}

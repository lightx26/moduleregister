package com.pet.moduleregister.application.moduleClassStudent.ports.out;

import com.pet.moduleregister.application.moduleClassStudent.dto.RegistrationPeriod;

import java.util.Optional;

public interface GetCurrentPeriodPort {
    Optional<RegistrationPeriod> getCurrentPeriod();
}

package com.pet.moduleregister.infrastructure.adapters.out.moduleClass;

import com.pet.moduleregister.application.moduleClass.dto.RegistrationPeriod;
import com.pet.moduleregister.application.moduleClass.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.registrationPeriod.ports.in.query.GetCurrentPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCurrentPeriodAdapter implements GetCurrentPeriodPort {
    private final GetCurrentPeriod getCurrentPeriod;
    @Override
    public RegistrationPeriod getCurrentPeriod() {
        com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod
                registrationPeriod = getCurrentPeriod.getCurrentPeriod();
        return new RegistrationPeriod(
                registrationPeriod.getSemesterId(),
                registrationPeriod.getStartTime(),
                registrationPeriod.getEndTime(),
                registrationPeriod.getType()
        );
    }
}

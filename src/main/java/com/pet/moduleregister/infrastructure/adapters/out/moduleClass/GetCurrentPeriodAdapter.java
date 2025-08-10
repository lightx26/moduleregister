package com.pet.moduleregister.infrastructure.adapters.out.moduleClass;

import com.pet.moduleregister.application.moduleClass.dto.usecases.RegistrationPeriod;
import com.pet.moduleregister.application.moduleClass.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.registrationPeriod.ports.in.query.GetCurrentPeriodQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCurrentPeriodAdapter implements GetCurrentPeriodPort {
    private final GetCurrentPeriodQuery getCurrentPeriodQuery;
    @Override
    public RegistrationPeriod getCurrentPeriod() {
        com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod
                registrationPeriod = getCurrentPeriodQuery.getCurrentPeriod();
        return new RegistrationPeriod(
                registrationPeriod.getSemesterId(),
                registrationPeriod.getStartTime(),
                registrationPeriod.getEndTime(),
                registrationPeriod.getType()
        );
    }
}

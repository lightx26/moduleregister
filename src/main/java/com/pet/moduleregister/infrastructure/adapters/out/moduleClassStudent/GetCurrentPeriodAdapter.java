package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent;

import com.pet.moduleregister.application.moduleClassStudent.dto.RegistrationPeriod;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.registrationPeriod.ports.in.query.GetCurrentPeriodQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("GetCurrentPeriodAdapterMCS")
@RequiredArgsConstructor
public class GetCurrentPeriodAdapter implements GetCurrentPeriodPort {
    private final GetCurrentPeriodQuery getCurrentPeriodQuery;
    @Override
    public Optional<RegistrationPeriod> getCurrentPeriod() {
        return getCurrentPeriodQuery.getCurrentPeriod()
                .map(registrationPeriod -> new RegistrationPeriod(
                    registrationPeriod.getSemesterId(),
                    registrationPeriod.getStartTime(),
                    registrationPeriod.getEndTime(),
                    registrationPeriod.getType()
                ));
    }
}

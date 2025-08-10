package com.pet.moduleregister.application.moduleClass.services.usecases;

import com.pet.moduleregister.application._shared.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClassDetails;
import com.pet.moduleregister.application.moduleClass.dto.usecases.RegistrationPeriod;
import com.pet.moduleregister.application.moduleClass.dto.usecases.Schedule;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetOpeningModuleClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.moduleClass.ports.out.GetSchedulesOfClassPort;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassDetailsImpl implements GetOpeningModuleClassDetails {

    private final GetCurrentPeriodPort getCurrentPeriod;
    private final ModuleClassRepositoryPort moduleClassRepository;
    private final GetSchedulesOfClassPort getSchedulesOfClass;

    @Override
    public OpeningClassDetails getOpeningModuleClassDetails(String classCode) {
        RegistrationPeriod currentPeriod = getCurrentPeriod.getCurrentPeriod()
                .orElseThrow(() -> new NotRegistrationTimeException(
                        "No current registration period found. Please check the registration periods."
                ));

        OpeningClassDetails classDetails = moduleClassRepository.findOpeningModuleClassByCode(classCode)
                .orElseThrow(
                        () -> new NotFoundException("Module class with code " + classCode + " not found")
                );

        if (!classDetails.getSemesterId().equals(currentPeriod.getSemesterId())) {
            throw new NotRegistrationTimeException(
                    "No current registration period found. Please check the registration periods."
            );
        }

        List<Schedule> schedules = getSchedulesOfClass.getSchedulesOfClass(classDetails.getModuleClassId()).stream()
                .map(schedule -> new Schedule(
                                schedule.getModuleClassId(),
                                schedule.getDayOfWeek(),
                                schedule.getStartPeriod(),
                                schedule.getEndPeriod(),
                                schedule.getRoom()
                        )
                ).toList();
        classDetails.setSchedules(schedules);
        return classDetails;
    }
}

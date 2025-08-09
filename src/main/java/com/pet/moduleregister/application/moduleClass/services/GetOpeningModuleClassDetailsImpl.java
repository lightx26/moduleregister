package com.pet.moduleregister.application.moduleClass.services;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;
import com.pet.moduleregister.application.moduleClass.dto.Schedule;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetOpeningModuleClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.out.GetSchedulesOfClassPort;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassDetailsImpl implements GetOpeningModuleClassDetails {

    private final ModuleClassRepositoryPort moduleClassRepository;
    private final GetSchedulesOfClassPort getSchedulesOfClass;

    @Override
    public OpeningClassDetails getOpeningModuleClassDetails(String classCode) {
        OpeningClassDetails classDetails = moduleClassRepository.findOpeningModuleClassByCode(classCode)
                .orElseThrow(
                        () -> new NotFoundException("Module class with code " + classCode + " not found")
                );
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

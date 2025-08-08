package com.pet.moduleregister.application.moduleClass.usecases;

import com.pet.moduleregister.application.classSchedule.ports.in.GetSchedulesOfClass;
import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;
import com.pet.moduleregister.application.moduleClass.dto.Schedule;
import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassDetailsImpl implements GetOpeningModuleClassDetails {

    private final ModuleClassRepositoryPort moduleClassRepository;
    private final GetSchedulesOfClass getSchedulesOfClass;

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

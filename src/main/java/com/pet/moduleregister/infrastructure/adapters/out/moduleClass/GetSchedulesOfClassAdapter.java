package com.pet.moduleregister.infrastructure.adapters.out.moduleClass;

import com.pet.moduleregister.application.classSchedule.ports.in.query.GetSchedulesOfClass;
import com.pet.moduleregister.application.moduleClass.dto.Schedule;
import com.pet.moduleregister.application.moduleClass.ports.out.GetSchedulesOfClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSchedulesOfClassAdapter implements GetSchedulesOfClassPort {
    private final GetSchedulesOfClass getSchedulesOfClass;
    @Override
    public List<Schedule> getSchedulesOfClass(Long... moduleClassIds) {
        return getSchedulesOfClass.getSchedulesOfClass(moduleClassIds).stream()
                .map(
                        schedule -> new Schedule(
                                schedule.getModuleClassId(),
                                schedule.getDayOfWeek(),
                                schedule.getStartPeriod(),
                                schedule.getEndPeriod(),
                                schedule.getRoom()
                        )
                )
                .toList();
    }
}

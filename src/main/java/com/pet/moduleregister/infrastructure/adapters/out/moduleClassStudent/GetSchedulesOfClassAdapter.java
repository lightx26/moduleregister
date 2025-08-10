package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent;

import com.pet.moduleregister.application.classSchedule.ports.in.query.GetSchedulesOfClassQuery;
import com.pet.moduleregister.application.moduleClassStudent.dto.ClassSchedule;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetSchedulesOfClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("getSchedulesOfClassAdapterMCS")
@RequiredArgsConstructor
public class GetSchedulesOfClassAdapter implements GetSchedulesOfClassPort {
    private final GetSchedulesOfClassQuery getSchedulesOfClassQuery;

    @Override
    public List<ClassSchedule> getSchedulesOfClass(Long... moduleClassIds) {
        return getSchedulesOfClassQuery.getSchedulesOfClass(moduleClassIds).stream()
                .map(
                        schedule -> new ClassSchedule(
                                schedule.getModuleClassId(),
                                schedule.getDayOfWeek(),
                                schedule.getStartPeriod(),
                                schedule.getEndPeriod()
                        )
                )
                .toList();
    }
}

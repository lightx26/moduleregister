package com.pet.moduleregister.application.classSchedule.ports.out;

import com.pet.moduleregister.application.classSchedule.dto.Schedule;

import java.util.List;

public interface ClassScheduleRepositoryPort {
    List<Schedule> getSchedulesByModuleClassIds(Long... moduleClassIds);
}

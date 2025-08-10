package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.usecases.Schedule;

import java.util.List;

public interface GetSchedulesOfClassPort {
    List<Schedule> getSchedulesOfClass(Long... moduleClassIds);
}

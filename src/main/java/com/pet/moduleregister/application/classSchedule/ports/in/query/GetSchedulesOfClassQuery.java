package com.pet.moduleregister.application.classSchedule.ports.in.query;


import com.pet.moduleregister.application.classSchedule.dto.Schedule;

import java.util.List;

public interface GetSchedulesOfClassQuery {
    List<Schedule> getSchedulesOfClass(Long... moduleClassIds);
}

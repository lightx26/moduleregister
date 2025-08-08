package com.pet.moduleregister.application.classSchedule.ports.in;


import com.pet.moduleregister.application.classSchedule.dto.Schedule;

import java.util.List;

public interface GetSchedulesOfClass {
    List<Schedule> getSchedulesOfClass(Long... moduleClassIds);
}

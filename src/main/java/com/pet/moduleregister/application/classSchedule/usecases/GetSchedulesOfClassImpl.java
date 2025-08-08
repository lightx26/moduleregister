package com.pet.moduleregister.application.classSchedule.usecases;

import com.pet.moduleregister.application.classSchedule.dto.Schedule;
import com.pet.moduleregister.application.classSchedule.ports.in.GetSchedulesOfClass;
import com.pet.moduleregister.application.classSchedule.ports.out.ClassScheduleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSchedulesOfClassImpl implements GetSchedulesOfClass {

    private final ClassScheduleRepositoryPort classScheduleRepository;
    @Override
    public List<Schedule> getSchedulesOfClass(Long... moduleClassIds) {
        return classScheduleRepository.getSchedulesByModuleClassIds(moduleClassIds);
    }
}

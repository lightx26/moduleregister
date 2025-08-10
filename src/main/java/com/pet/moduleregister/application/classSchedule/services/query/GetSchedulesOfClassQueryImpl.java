package com.pet.moduleregister.application.classSchedule.services.query;

import com.pet.moduleregister.application.classSchedule.dto.Schedule;
import com.pet.moduleregister.application.classSchedule.ports.in.query.GetSchedulesOfClassQuery;
import com.pet.moduleregister.application.classSchedule.ports.out.ClassScheduleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSchedulesOfClassQueryImpl implements GetSchedulesOfClassQuery {

    private final ClassScheduleRepositoryPort classScheduleRepository;
    @Override
    public List<Schedule> getSchedulesOfClass(Long... moduleClassIds) {
        return classScheduleRepository.getSchedulesByModuleClassIds(moduleClassIds);
    }
}

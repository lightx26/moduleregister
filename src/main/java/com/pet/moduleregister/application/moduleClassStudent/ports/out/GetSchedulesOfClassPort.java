package com.pet.moduleregister.application.moduleClassStudent.ports.out;

import com.pet.moduleregister.application.moduleClassStudent.dto.ClassSchedule;

import java.util.List;

public interface GetSchedulesOfClassPort {
    List<ClassSchedule> getSchedulesOfClass(Long... moduleClassIds);
}
package com.pet.moduleregister.application.semester.ports.out;

import com.pet.moduleregister.domain.semester.Semester;

import java.util.Optional;

public interface SemesterRepositoryPort {
    Optional<Semester> getCurrentSemester();
    void setActiveSemester(Long semesterId);
    boolean existsById(Long semesterId);
}

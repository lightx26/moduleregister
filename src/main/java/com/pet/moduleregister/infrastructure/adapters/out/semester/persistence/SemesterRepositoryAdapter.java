package com.pet.moduleregister.infrastructure.adapters.out.semester.persistence;

import com.pet.moduleregister.infrastructure.adapters.out.semester.persistence.mappers.SemesterDomainMapper;
import com.pet.moduleregister.application.semester.ports.out.SemesterRepositoryPort;
import com.pet.moduleregister.entities.semester.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class SemesterRepositoryAdapter implements SemesterRepositoryPort {

    private final SemesterJpaRepository semesterJpaRepository;
    private final SemesterDomainMapper semesterDomainMapper;

    @Override
    public Optional<Semester> getCurrentSemester() {
        return semesterJpaRepository.findFirstByActive(true)
                .map(semesterDomainMapper::toDomain);
    }

    @Override
    public void setActiveSemester(Long semesterId) {
        semesterJpaRepository.setActiveSemester(semesterId);
    }

    @Override
    public boolean existsById(Long semesterId) {
        return semesterJpaRepository.existsById(semesterId);
    }
}

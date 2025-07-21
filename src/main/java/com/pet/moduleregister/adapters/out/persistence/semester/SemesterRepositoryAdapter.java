package com.pet.moduleregister.adapters.out.persistence.semester;

import com.pet.moduleregister.adapters.out.persistence.semester.mappers.SemesterDomainMapper;
import com.pet.moduleregister.application.semester.ports.out.SemesterRepositoryPort;
import com.pet.moduleregister.domain.semester.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class SemesterRepositoryAdapter implements SemesterRepositoryPort {

    private final SemesterJpaRepository semesterJpaRepository;
    private final SemesterDomainMapper semesterDomainMapper;
    private final RedisTemplate<String, String> redisTemplate;
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

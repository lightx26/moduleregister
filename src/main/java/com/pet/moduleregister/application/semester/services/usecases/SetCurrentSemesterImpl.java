package com.pet.moduleregister.application.semester.services.usecases;

import com.pet.moduleregister.application.semester.ports.in.usecases.SetCurrentSemester;
import com.pet.moduleregister.application.semester.ports.out.SemesterRepositoryPort;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SetCurrentSemesterImpl implements SetCurrentSemester {
    private final SemesterRepositoryPort semesterRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public void setCurrentSemester(Long semesterId) {
        if (semesterRepository.existsById(semesterId)) {
            // Update the active semester in the database
            semesterRepository.setActiveSemester(semesterId);
        } else {
            throw new NotFoundException("Semester with ID " + semesterId + " does not exist.");
        }

        // Clear the cache
        redisTemplate.delete("currentSemester");
    }
}

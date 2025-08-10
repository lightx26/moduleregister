package com.pet.moduleregister.application.semester.services.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.moduleregister.application.semester.ports.in.usecases.GetCurrentSemester;
import com.pet.moduleregister.application.semester.ports.out.SemesterRepositoryPort;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import com.pet.moduleregister.entities.semester.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GetCurrentSemesterImpl implements GetCurrentSemester {
    private final SemesterRepositoryPort semesterRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Semester getCurrentSemester() {

        String cacheKey = "currentSemester";
        String cached = redisTemplate.opsForValue().get(cacheKey);

        if (!(cached == null || cached.isEmpty())) {
            return deserializeSemester(cached);
        }

        Semester currentSemester = semesterRepository.getCurrentSemester().orElseThrow(
                () -> new NotFoundException("No active semester found")
        );

        Duration ttl = Duration.between(Instant.now(), currentSemester.getEndDate());

        redisTemplate.opsForValue().set(
                cacheKey,
                serializeSemester(currentSemester),
                ttl // TTL until the end of the semester
        );

        return currentSemester;
    }

    private String serializeSemester(Semester semester) {
        try {
            return objectMapper.writeValueAsString(semester);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization error", e);
        }
    }

    private Semester deserializeSemester(String json) {
        try {
            return objectMapper.readValue(json, Semester.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Deserialization error", e);
        }
    }
}

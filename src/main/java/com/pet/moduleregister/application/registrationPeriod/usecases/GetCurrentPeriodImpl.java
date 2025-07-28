package com.pet.moduleregister.application.registrationPeriod.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.moduleregister.application.registrationPeriod.ports.in.GetCurrentPeriodUsecase;
import com.pet.moduleregister.application.registrationPeriod.ports.out.RegistrationPeriodRepositoryPort;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GetCurrentPeriodImpl implements GetCurrentPeriodUsecase {
    private final RegistrationPeriodRepositoryPort registrationPeriodRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public RegistrationPeriod getCurrentPeriod() {

        String cacheKey = "currentRegistrationPeriod";
        String cached = redisTemplate.opsForValue().get(cacheKey);

        if (!(cached == null || cached.isEmpty())) {
            return deserializeRegistration(cached);
        }

        RegistrationPeriod currentPeriod = registrationPeriodRepository.getPeriodByDate(Instant.now()).orElseThrow(
                () -> new NotFoundException("No current registration period found")
        );

        Duration ttl = Duration.between(Instant.now(), currentPeriod.getEndTime());

        redisTemplate.opsForValue().set(
                cacheKey,
                serializeRegistration(currentPeriod),
                ttl // TTL until the end of the registration period
        );

        return currentPeriod;
    }

    private String serializeRegistration(RegistrationPeriod period) {
        try {
            return objectMapper.writeValueAsString(period);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization error", e);
        }
    }

    private RegistrationPeriod deserializeRegistration(String json) {
        try {
            return objectMapper.readValue(json, RegistrationPeriod.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Deserialization error", e);
        }
    }
}

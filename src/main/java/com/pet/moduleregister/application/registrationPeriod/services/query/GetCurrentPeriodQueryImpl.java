package com.pet.moduleregister.application.registrationPeriod.services.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.moduleregister.application.registrationPeriod.ports.in.query.GetCurrentPeriodQuery;
import com.pet.moduleregister.application.registrationPeriod.ports.out.RegistrationPeriodRepositoryPort;
import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCurrentPeriodQueryImpl implements GetCurrentPeriodQuery {
    private final RegistrationPeriodRepositoryPort registrationPeriodRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<RegistrationPeriod> getCurrentPeriod() {
        final String cacheKey = "currentRegistrationPeriod";
        String cached = redisTemplate.opsForValue().get(cacheKey);

        if (StringUtils.hasText(cached)) {
            return Optional.ofNullable(deserializeRegistration(cached));
        }

        Instant now = Instant.now();
        return registrationPeriodRepository.getPeriodByDate(now)
                .map(period -> {
                    Duration ttl = Duration.between(now, period.getEndTime());
                    if (!ttl.isNegative() && !ttl.isZero()) {
                        redisTemplate.opsForValue().set(cacheKey, serializeRegistration(period), ttl);
                    }
                    return period;
                });
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

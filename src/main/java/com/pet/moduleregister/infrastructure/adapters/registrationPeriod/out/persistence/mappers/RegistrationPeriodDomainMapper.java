package com.pet.moduleregister.infrastructure.adapters.registrationPeriod.out.persistence.mappers;

import com.pet.moduleregister.infrastructure.adapters.registrationPeriod.out.persistence.RegistrationPeriodEntity;
import com.pet.moduleregister.infrastructure.adapters.shared.out.mapper.DomainMapper;
import com.pet.moduleregister.domain.registrationPeriod.RegistrationPeriod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationPeriodDomainMapper implements DomainMapper<RegistrationPeriod, RegistrationPeriodEntity> {
    @Override
    public RegistrationPeriod toDomain(RegistrationPeriodEntity entity) {
        return new RegistrationPeriod(
                entity.getRegistrationPeriodId(),
                entity.getSemesterId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public List<RegistrationPeriod> toDomain(List<RegistrationPeriodEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}

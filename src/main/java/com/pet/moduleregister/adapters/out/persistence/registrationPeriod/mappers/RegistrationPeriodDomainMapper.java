package com.pet.moduleregister.adapters.out.persistence.registrationPeriod.mappers;

import com.pet.moduleregister.adapters.out.persistence.registrationPeriod.RegistrationPeriodEntity;
import com.pet.moduleregister.adapters.out.persistence.shared.mapper.DomainMapper;
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

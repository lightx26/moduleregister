package com.pet.moduleregister.infrastructure.adapters.out.semester.persistence.mappers;

import com.pet.moduleregister.infrastructure.adapters.out.semester.persistence.SemesterEntity;
import com.pet.moduleregister.infrastructure.adapters.out._shared.mapper.DomainMapper;
import com.pet.moduleregister.entities.semester.Semester;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SemesterDomainMapper implements DomainMapper<Semester, SemesterEntity> {
    @Override
    public Semester toDomain(SemesterEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Semester(
                entity.getSemesterId(),
                entity.getSemesterName(),
                entity.getOrderInYear(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public List<Semester> toDomain(List<SemesterEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}

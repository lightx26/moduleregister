package com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence.mappers;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence.ModuleClassEntity;
import com.pet.moduleregister.infrastructure.adapters.shared.out.mapper.DomainMapper;
import com.pet.moduleregister.entities.moduleClass.ModuleClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleClassDomainMapper implements DomainMapper<ModuleClass, ModuleClassEntity> {

    @Override
    public ModuleClass toDomain(ModuleClassEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ModuleClass(
                entity.getModuleClassId(),
                entity.getModuleClassCode(),
                entity.getMaxParticipants(),
                entity.getModuleId(),
                entity.getLecturerId(),
                entity.getSemesterId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public List<ModuleClass> toDomain(List<ModuleClassEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}

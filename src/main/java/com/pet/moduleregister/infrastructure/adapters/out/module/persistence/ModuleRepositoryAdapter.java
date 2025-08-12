package com.pet.moduleregister.infrastructure.adapters.out.module.persistence;

import com.pet.moduleregister.application.module.dto.ModuleBasicDTO;
import com.pet.moduleregister.application.module.ports.out.ModuleRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ModuleRepositoryAdapter implements ModuleRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ModuleBasicDTO> getModulesOfProgram(Long programId) {
        String jpql = """
                SELECT
                    m.moduleId as moduleId,
                    m.moduleCode as moduleCode,
                    m.moduleName as moduleName,
                    m.numberOfCredits as numberOfCredits,
                    pm.electiveGroupId as electiveGroupId
                FROM ProgramModuleEntity pm
                LEFT JOIN ElectiveModuleEntity em
                    ON pm.electiveGroupId = em.electiveGroupId
                JOIN ModuleEntity m
                    ON pm.moduleId = m.moduleId or em.moduleId = m.moduleId
                WHERE pm.programId = :programId
                """;

        TypedQuery<Tuple> query = entityManager.createQuery(jpql, Tuple.class);
        query.setParameter("programId", programId);

        List<Tuple> results = query.getResultList();

        return results.stream().map(
            tuple -> new ModuleBasicDTO(
                tuple.get("moduleId", Long.class),
                tuple.get("moduleCode", String.class),
                tuple.get("moduleName", String.class),
                tuple.get("numberOfCredits", BigDecimal.class),
                tuple.get("electiveGroupId", Long.class) == null
            )
        ).toList();
    }
}

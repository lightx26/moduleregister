package com.pet.moduleregister.infrastructure.adapters.out.moduleClass.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModuleClassJpaRepository extends JpaRepository<ModuleClassEntity, Long> {
    @Query("SELECT m FROM ModuleClassEntity m WHERE m.semesterId = ?1")
    List<ModuleClassEntity> findBySemesterId(Long semesterId);

    Optional<ModuleClassEntity> findByModuleClassCode(String classCode);
    boolean existsByModuleClassCode(String classCode);
}

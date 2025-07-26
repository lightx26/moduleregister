package com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleClassJpaRepository extends JpaRepository<ModuleClassEntity, Long> {
    @Query("SELECT m FROM ModuleClassEntity m WHERE m.semesterId = ?1")
    List<ModuleClassEntity> findBySemesterId(Long semesterId);
}

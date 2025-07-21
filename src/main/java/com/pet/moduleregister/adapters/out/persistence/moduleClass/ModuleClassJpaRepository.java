package com.pet.moduleregister.adapters.out.persistence.moduleClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleClassJpaRepository extends JpaRepository<ModuleClassEntity, Long> {
    @Query("SELECT m FROM ModuleClassEntity m WHERE m.semesterId = ?1")
    List<ModuleClassEntity> findBySemesterId(Long semesterId);
}

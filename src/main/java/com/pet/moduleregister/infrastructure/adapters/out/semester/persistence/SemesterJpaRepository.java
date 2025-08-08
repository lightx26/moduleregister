package com.pet.moduleregister.infrastructure.adapters.out.semester.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SemesterJpaRepository extends JpaRepository<SemesterEntity, Long> {
    @Query("SELECT s FROM SemesterEntity s WHERE s.isActive = ?1 ORDER BY s.startDate DESC")
    Optional<SemesterEntity> findFirstByActive(boolean active);

    @Modifying
    @Query("UPDATE SemesterEntity s SET s.isActive = (CASE WHEN s.semesterId = ?1 THEN true ELSE false END)")
    void setActiveSemester(Long semesterId);
}

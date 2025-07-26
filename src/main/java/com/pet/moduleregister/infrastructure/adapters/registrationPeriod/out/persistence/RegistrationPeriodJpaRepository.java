package com.pet.moduleregister.infrastructure.adapters.registrationPeriod.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface RegistrationPeriodJpaRepository extends JpaRepository<RegistrationPeriodEntity, Long> {
    @Query("SELECT rp FROM RegistrationPeriodEntity rp WHERE ?1 BETWEEN rp.startTime AND rp.endTime")
    Optional<RegistrationPeriodEntity> findByDate(Instant date);
}

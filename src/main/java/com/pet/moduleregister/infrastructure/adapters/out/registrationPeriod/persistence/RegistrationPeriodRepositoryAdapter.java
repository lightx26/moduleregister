package com.pet.moduleregister.infrastructure.adapters.out.registrationPeriod.persistence;

import com.pet.moduleregister.infrastructure.adapters.out.registrationPeriod.persistence.mappers.RegistrationPeriodDomainMapper;
import com.pet.moduleregister.application.registrationPeriod.ports.out.RegistrationPeriodRepositoryPort;
import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegistrationPeriodRepositoryAdapter implements RegistrationPeriodRepositoryPort {
    private final RegistrationPeriodJpaRepository registrationPeriodJpaRepository;
    private final RegistrationPeriodDomainMapper registrationPeriodDomainMapper;

    @Override
    public Optional<RegistrationPeriod> getPeriodByDate(Instant date) {
        return registrationPeriodJpaRepository.findByDate(date)
                .map(registrationPeriodDomainMapper::toDomain);
    }
}

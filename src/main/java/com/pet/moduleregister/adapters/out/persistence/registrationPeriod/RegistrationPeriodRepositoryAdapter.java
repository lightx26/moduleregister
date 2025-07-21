package com.pet.moduleregister.adapters.out.persistence.registrationPeriod;

import com.pet.moduleregister.adapters.out.persistence.registrationPeriod.mappers.RegistrationPeriodDomainMapper;
import com.pet.moduleregister.application.registrationPeriod.ports.out.RegistrationPeriodRepositoryPort;
import com.pet.moduleregister.domain.registrationPeriod.RegistrationPeriod;
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

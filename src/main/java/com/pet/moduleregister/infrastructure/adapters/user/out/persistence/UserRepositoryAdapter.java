package com.pet.moduleregister.infrastructure.adapters.user.out.persistence;

import com.pet.moduleregister.infrastructure.adapters.user.out.persistence.mappers.UserDomainMapper;
import com.pet.moduleregister.entities.user.User;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserDomainMapper userDomainMapper;

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId)
                .map(userDomainMapper::toDomain);
    }

    @Override
    public Optional<User> findByCode(String userCode) {
        return userJpaRepository.findByUserCode(userCode)
                .map(userDomainMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userDomainMapper.toDomain(userJpaRepository.findAll());
    }
}

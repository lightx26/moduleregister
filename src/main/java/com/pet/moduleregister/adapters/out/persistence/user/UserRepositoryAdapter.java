package com.pet.moduleregister.adapters.out.persistence.user;

import com.pet.moduleregister.adapters.out.persistence.user.mappers.UserDomainMapper;
import com.pet.moduleregister.domain.user.model.User;
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
    public Optional<User> findById(String userId) {
        return userJpaRepository.findById(userId)
                .map(userDomainMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userDomainMapper.toDomain(userJpaRepository.findAll());
    }
}

package com.pet.moduleregister.application.user.services.query;

import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.entities.user.User;
import com.pet.moduleregister.application.user.ports.in.query.GetUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserQueryImpl implements GetUserQuery {

    private final UserRepositoryPort userRepository;

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByCode(String userCode) {
        return userRepository.findByCode(userCode);
    }
}

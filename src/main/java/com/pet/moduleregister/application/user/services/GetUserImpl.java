package com.pet.moduleregister.application.user.services;

import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.entities.user.User;
import com.pet.moduleregister.application.user.ports.in.query.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserImpl implements GetUser {

    private final UserRepositoryPort userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " does not exist."));
    }

    @Override
    public User getUserByCode(String userCode) {
        return userRepository.findByCode(userCode)
                .orElseThrow(() -> new NotFoundException("User with code " + userCode + " does not exist."));
    }
}

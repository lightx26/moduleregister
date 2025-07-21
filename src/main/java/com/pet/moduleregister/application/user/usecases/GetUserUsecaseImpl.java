package com.pet.moduleregister.application.user.usecases;

import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.domain.user.User;
import com.pet.moduleregister.application.user.ports.in.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUsecaseImpl implements GetUserUsecase {

    private final UserRepositoryPort userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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

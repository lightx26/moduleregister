package com.pet.moduleregister.application.user;

import com.pet.moduleregister.adapters.out.persistence.user.UserRepository;
import com.pet.moduleregister.domain.modules.user.model.User;
import com.pet.moduleregister.domain.modules.user.ports.in.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUsecaseImpl implements GetUserUsecase {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}

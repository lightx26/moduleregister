package com.pet.moduleregister.application.user.usecases;

import com.pet.moduleregister.adapters.out.persistence.user.UserRepositoryAdapter;
import com.pet.moduleregister.domain.user.User;
import com.pet.moduleregister.application.user.ports.in.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUsecaseImpl implements GetUserUsecase {

    private final UserRepositoryAdapter userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

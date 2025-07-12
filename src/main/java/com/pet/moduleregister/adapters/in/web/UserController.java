package com.pet.moduleregister.adapters.in.web;

import com.pet.moduleregister.domain.modules.user.ports.in.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final GetUserUsecase getUserUsecase;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        // Implement the logic to retrieve all users
        var users = getUserUsecase.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
}

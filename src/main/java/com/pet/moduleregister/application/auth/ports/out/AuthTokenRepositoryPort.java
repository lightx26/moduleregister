package com.pet.moduleregister.application.auth.ports.out;

import com.pet.moduleregister.application.auth.dto.AuthTokenDTO;

import java.time.Instant;
import java.util.Optional;

public interface AuthTokenRepositoryPort {
    void create(AuthTokenDTO authTokenDTO);
    void update(AuthTokenDTO authTokenDTO);
    void update(String authTokenId, String refreshToken, Instant updatedAt);
    Optional<AuthTokenDTO> findByUserIdAndToken(Long userId, String refreshToken);
    void deleteById(String authTokenId);
}

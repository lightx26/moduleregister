package com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface AuthTokenJpaRepository extends JpaRepository<AuthTokenEntity, String> {
    @Query("SELECT a FROM AuthTokenEntity a WHERE a.userId = ?1 AND a.refreshToken = ?2")
    Optional<AuthTokenEntity> findByUserIdAndRefreshToken(Long userId, String refreshToken);

    @Modifying
    @Query("UPDATE AuthTokenEntity a SET a.refreshToken = ?2, a.updatedAt = ?3 WHERE a.authTokenId = ?1")
    void updateRefreshToken(Long authTokenId, String refreshToken, Instant updatedAt);
}

package com.pet.moduleregister.adapters.out.persistence.authToken;

import com.pet.moduleregister.adapters.out.persistence.authToken.mappers.AuthTokenDomainMapper;
import com.pet.moduleregister.adapters.out.persistence.authToken.mappers.AuthTokenEntityMapper;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthTokenRepositoryAdapter implements AuthTokenRepositoryPort {

    private final AuthTokenJpaRepository authTokenJpaRepository;
    private final AuthTokenEntityMapper authTokenEntityMapper;
    private final AuthTokenDomainMapper authTokenDomainMapper;
    @Override
    public void create(AuthTokenDTO authTokenDTO) {
        AuthTokenEntity authTokenEntity = authTokenEntityMapper.toEntity(authTokenDTO);
        authTokenJpaRepository.save(authTokenEntity);
    }

    @Override
    public void update(AuthTokenDTO authTokenDTO) {
        AuthTokenEntity entity = authTokenJpaRepository.findById(authTokenDTO.getAuthTokenId())
                .orElseThrow(() -> new IllegalArgumentException("Auth token not found"));

        entity.setRefreshToken(authTokenDTO.getRefreshToken());
        entity.setUpdatedAt(authTokenDTO.getUpdatedAt());
        authTokenJpaRepository.save(entity);
    }

    @Override
    public void update(String authTokenId, String refreshToken, Instant updatedAt) {
        authTokenJpaRepository.updateRefreshToken(authTokenId, refreshToken, updatedAt);
    }

    @Override
    public Optional<AuthTokenDTO> findByUserIdAndToken(String userId, String refreshToken) {
        Optional<AuthTokenEntity> authTokenEntity = authTokenJpaRepository.findByUserIdAndRefreshToken(userId, refreshToken);
        return authTokenEntity.map(authTokenDomainMapper::toDomain);
    }

    @Override
    public void deleteById(String authTokenId) {
        authTokenJpaRepository.deleteById(authTokenId);
    }
}

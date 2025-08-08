package com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken.mappers;

import com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken.AuthTokenEntity;
import com.pet.moduleregister.infrastructure.adapters.out._shared.mapper.DomainMapper;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthTokenDomainMapper implements DomainMapper<AuthTokenDTO, AuthTokenEntity> {

    @Override
    public AuthTokenDTO toDomain(AuthTokenEntity entity) {
        if (entity == null) {
            return null;
        }
        return AuthTokenDTO.builder()
                .authTokenId(entity.getAuthTokenId().toString())
                .userId(entity.getUserId())
                .refreshToken(entity.getRefreshToken())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public List<AuthTokenDTO> toDomain(List<AuthTokenEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}

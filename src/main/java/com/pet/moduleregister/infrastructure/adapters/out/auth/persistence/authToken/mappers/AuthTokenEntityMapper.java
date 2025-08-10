package com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken.mappers;

import com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken.AuthTokenEntity;
import com.pet.moduleregister.infrastructure.adapters.out._shared.mapper.EntityMapper;
import com.pet.moduleregister.application.auth.dto.AuthTokenDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthTokenEntityMapper implements EntityMapper<AuthTokenDTO, AuthTokenEntity> {
    @Override
    public AuthTokenEntity toEntity(AuthTokenDTO dto) {
        if (dto == null) {
            return null;
        }
        return AuthTokenEntity.builder()
                .authTokenId(
                        dto.getAuthTokenId() != null ? Long.parseLong(dto.getAuthTokenId()) : null
                )
                .userId(dto.getUserId())
                .refreshToken(dto.getRefreshToken())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    @Override
    public List<AuthTokenEntity> toEntity(List<AuthTokenDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}

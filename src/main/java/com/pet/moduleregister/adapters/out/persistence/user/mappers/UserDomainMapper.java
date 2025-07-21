package com.pet.moduleregister.adapters.out.persistence.user.mappers;

import com.pet.moduleregister.adapters.out.persistence.shared.mapper.DomainMapper;
import com.pet.moduleregister.adapters.out.persistence.user.UserEntity;
import com.pet.moduleregister.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDomainMapper implements DomainMapper<User, UserEntity> {
    @Override
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getUserId(),
                entity.getUserCode(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getCitizenId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getGender(),
                entity.getDateOfBirth(),
                entity.getRole()
        );
    }

    @Override
    public List<User> toDomain(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}

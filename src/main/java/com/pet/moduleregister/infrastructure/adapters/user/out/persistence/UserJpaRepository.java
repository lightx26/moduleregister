package com.pet.moduleregister.infrastructure.adapters.user.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.userCode = ?1")
    Optional<UserEntity> findByUserCode(String userCode);
}

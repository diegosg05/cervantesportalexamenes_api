package com.cervantes.pe.exam_platform.user.infrastructure.repository;

import com.cervantes.pe.exam_platform.role.infrastructure.persistence.RoleEntity;
import com.cervantes.pe.exam_platform.user.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRole(RoleEntity roleEntity);
}

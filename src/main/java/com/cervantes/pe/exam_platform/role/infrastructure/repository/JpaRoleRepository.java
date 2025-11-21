package com.cervantes.pe.exam_platform.role.infrastructure.repository;

import com.cervantes.pe.exam_platform.role.infrastructure.persistence.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
}

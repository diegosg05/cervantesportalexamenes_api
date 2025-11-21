package com.cervantes.pe.exam_platform.role.application.in;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;

import java.util.Optional;

public interface GetRoleByIdCase {
    Optional<Role> getById(Long id);
}

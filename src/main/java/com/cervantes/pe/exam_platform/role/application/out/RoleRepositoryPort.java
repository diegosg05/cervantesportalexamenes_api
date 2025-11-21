package com.cervantes.pe.exam_platform.role.application.out;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    Role save(Role role);
    Optional<Role> getById(Long id);
    List<Role> getAll();
    void update(Role role);
    void delete(Long id);
}

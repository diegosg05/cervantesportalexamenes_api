package com.cervantes.pe.exam_platform.role.application.in;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;

public interface CreateRoleCase {
    Role save(Role role);
}

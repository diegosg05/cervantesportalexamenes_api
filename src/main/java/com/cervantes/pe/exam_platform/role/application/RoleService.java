package com.cervantes.pe.exam_platform.role.application;

import com.cervantes.pe.exam_platform.role.application.in.*;
import com.cervantes.pe.exam_platform.role.application.out.RoleRepositoryPort;
import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements CreateRoleCase, GetRoleByIdCase, GetAllRolesCase,
        UpdateRoleCase, DeleteRoleCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role save(Role role) {
        return roleRepositoryPort.save(role);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepositoryPort.getById(id);
    }

    @Override
    public void delete(Long id) {
        roleRepositoryPort.delete(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepositoryPort.getAll();
    }

    @Override
    public void update(Role role) {
        roleRepositoryPort.update(role);
    }
}

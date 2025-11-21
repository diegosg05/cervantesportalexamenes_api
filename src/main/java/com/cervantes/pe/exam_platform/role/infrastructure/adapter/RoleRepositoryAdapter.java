package com.cervantes.pe.exam_platform.role.infrastructure.adapter;

import com.cervantes.pe.exam_platform.role.application.out.RoleRepositoryPort;
import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.role.infrastructure.mapper.RoleMapper;
import com.cervantes.pe.exam_platform.role.infrastructure.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        var roleSaved = jpaRoleRepository.save(roleMapper.fromRoleToEntity(role));
        return roleMapper.fromEntityToRole(roleSaved);
    }

    @Override
    public Optional<Role> getById(Long id) {
        var roleOptional = jpaRoleRepository.findById(id);
        return roleOptional
                .map(roleMapper::fromEntityToRole);
    }

    @Override
    public List<Role> getAll() {
        return jpaRoleRepository.findAll()
                .stream()
                .map(roleMapper::fromEntityToRole)
                .toList();
    }

    @Override
    public void update(Role role) {
        jpaRoleRepository.save(roleMapper.fromRoleToEntity(role));
    }

    @Override
    public void delete(Long id) {
        jpaRoleRepository.deleteById(id);
    }
}

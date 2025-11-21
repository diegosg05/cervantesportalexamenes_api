package com.cervantes.pe.exam_platform.role.infrastructure.mapper.impl;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.role.infrastructure.dto.RoleDto;
import com.cervantes.pe.exam_platform.role.infrastructure.mapper.RoleMapper;
import com.cervantes.pe.exam_platform.role.infrastructure.persistence.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleEntity fromRoleToEntity(Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public Role fromEntityToRole(RoleEntity roleEntity) {
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }

    @Override
    public RoleDto fromRoleToDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

    @Override
    public Role fromDtoToRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.id())
                .name(roleDto.name())
                .build();
    }
}

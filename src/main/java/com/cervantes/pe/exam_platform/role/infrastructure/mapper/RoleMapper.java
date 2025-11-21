package com.cervantes.pe.exam_platform.role.infrastructure.mapper;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.role.infrastructure.dto.RoleDto;
import com.cervantes.pe.exam_platform.role.infrastructure.persistence.RoleEntity;

public interface RoleMapper {
    RoleEntity fromRoleToEntity(Role role);
    Role fromEntityToRole(RoleEntity roleEntity);
    RoleDto fromRoleToDto(Role role);
    Role fromDtoToRole(RoleDto roleDto);
}

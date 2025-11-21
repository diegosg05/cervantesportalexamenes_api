package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import com.cervantes.pe.exam_platform.role.infrastructure.dto.RoleDto;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstname,
        String lastname,
        String phone,
        String image,
        Boolean enabled,
        RoleDto role
) {
}

package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(
        @NotBlank String username,
        @NotBlank String password
) {
}

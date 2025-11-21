package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotBlank String phone,
        @NotBlank String image
) {
}

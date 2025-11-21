package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password,
        @NotBlank String email,
        @NotBlank String firstname,
        @NotBlank String lastname,
        String phone,
        @NotNull Boolean enabled,
        String image,
        @Min(1) Long idRole
) {
}

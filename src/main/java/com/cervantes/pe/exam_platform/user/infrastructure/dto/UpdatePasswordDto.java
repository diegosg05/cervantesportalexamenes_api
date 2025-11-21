package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDto(
        @NotBlank String oldPassword,
        @NotBlank @Size(min = 6) String newPassword
) {
}

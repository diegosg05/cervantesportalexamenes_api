package com.cervantes.pe.exam_platform.user.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterStudentDto(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password,
        @NotBlank String email,
        @NotBlank String firstname,
        @NotBlank String lastname,
        String phone
) {
}

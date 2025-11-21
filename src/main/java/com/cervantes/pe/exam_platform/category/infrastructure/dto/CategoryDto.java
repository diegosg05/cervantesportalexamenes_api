package com.cervantes.pe.exam_platform.category.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
        Long id,
        @NotBlank String title,
        @NotBlank String description
) {
}

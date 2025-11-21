package com.cervantes.pe.exam_platform.common.response;

import com.cervantes.pe.exam_platform.common.exception.ErrorMessage;

public record ApiResponse<T>(
        T data,
        ErrorMessage error
) {
}

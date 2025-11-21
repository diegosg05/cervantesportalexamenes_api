package com.cervantes.pe.exam_platform.common.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorMessage {
    private String message;
    private String exception;
    private String path;
    private Map<String, String> error;

    public ErrorMessage(String message, String exception, String path) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.error = new HashMap<>();
    }

    public ErrorMessage(String message, String exception, String path, Map<String, String> error) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.error = error;
    }
}


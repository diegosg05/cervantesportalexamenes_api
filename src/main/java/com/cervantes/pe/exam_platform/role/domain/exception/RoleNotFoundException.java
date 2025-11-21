package com.cervantes.pe.exam_platform.role.domain.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role with id " + id + " was not founded");
    }
}

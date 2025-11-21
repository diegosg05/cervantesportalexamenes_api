package com.cervantes.pe.exam_platform.user.application.in;

public interface UpdatePasswordCase {
    void updatePassword(String username, String oldPassword, String newPassword);
}

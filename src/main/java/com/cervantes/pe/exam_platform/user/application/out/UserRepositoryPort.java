package com.cervantes.pe.exam_platform.user.application.out;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.user.domain.entity.User;

import java.util.List;

public interface UserRepositoryPort {
    User save(User user);
    User getByUsername(String username);
    User login(String username, String password);
    List<User> getAll();
    List<User> getByRole(Role role);
    void updateEnabled(Long id);
    User update(User user);
    void updatePassword(String username, String oldPassword, String newPassword);
}

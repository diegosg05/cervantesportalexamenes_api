package com.cervantes.pe.exam_platform.user.application;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.user.application.in.*;
import com.cervantes.pe.exam_platform.user.application.out.UserRepositoryPort;
import com.cervantes.pe.exam_platform.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserCase, GetByUsernameCase, LoginUserCase, GetAllUsersCase,
        GetUsersByRole, UpdateEnabledCase, UpdatePasswordCase, UpdateUserCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User save(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepositoryPort.getByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        return userRepositoryPort.login(username, password);
    }

    @Override
    public List<User> getAll() {
        return userRepositoryPort.getAll();
    }

    @Override
    public List<User> getByRole(Role role) {
        return userRepositoryPort.getByRole(role);
    }

    @Override
    public void updateEnabled(Long id) {
        userRepositoryPort.updateEnabled(id);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        userRepositoryPort.updatePassword(username, oldPassword, newPassword);
    }

    @Override
    public User update(User user) {
        return userRepositoryPort.update(user);
    }
}

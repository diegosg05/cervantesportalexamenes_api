package com.cervantes.pe.exam_platform.user.infrastructure.adapter;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import com.cervantes.pe.exam_platform.role.infrastructure.mapper.RoleMapper;
import com.cervantes.pe.exam_platform.user.application.out.UserRepositoryPort;
import com.cervantes.pe.exam_platform.user.domain.entity.User;
import com.cervantes.pe.exam_platform.user.domain.exception.UserNotFoundException;
import com.cervantes.pe.exam_platform.user.infrastructure.mapper.UserMapper;
import com.cervantes.pe.exam_platform.user.infrastructure.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public User save(User user) {
        validateCredentialsRegister(user);
        var userEntity = userMapper.fromUserToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        var userSaved = jpaUserRepository.save(userEntity);
        return userMapper.fromEntityToUser(userSaved);
    }

    @Override
    public User getByUsername(String username) {
        var userOptional = jpaUserRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User was not found");
        }

        return userMapper.fromEntityToUser(userOptional.get());
    }

    @Override
    public User login(String username, String password) {
        var userOptional = jpaUserRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User was not found");
        }

        var userEntity = userOptional.get();

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("The password is incorrect");
        }

        if (!userEntity.getEnabled()) {
            throw new BadCredentialsException("The account is disabled");
        }

        return userMapper.fromEntityToUser(userEntity);
    }

    @Override
    public List<User> getAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(userMapper::fromEntityToUser)
                .toList();
    }

    @Override
    public List<User> getByRole(Role role) {
        return jpaUserRepository
                .findByRole(roleMapper.fromRoleToEntity(role))
                .stream()
                .map(userMapper::fromEntityToUser)
                .toList();
    }

    @Override
    public void updateEnabled(Long id) {
        var userOptional = jpaUserRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User was not found");
        }

        var user = userOptional.get();
        user.setEnabled(!user.getEnabled());
        jpaUserRepository.save(user);
    }

    @Override
    public User update(User user) {
        var userEntity = userMapper.fromUserToEntity(user);
        userEntity.setPassword(user.getPassword());
        return userMapper.fromEntityToUser(jpaUserRepository.save(userEntity));
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        var userOptional = jpaUserRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User was not found");
        }

        var user = userOptional.get();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("The password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        jpaUserRepository.save(user);
    }

    private void validateCredentialsRegister(User user) {
        var userOptional = jpaUserRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new BadCredentialsException("The username is already taken");
        }

        userOptional = jpaUserRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            throw new BadCredentialsException("The email is already taken");
        }
    }
}

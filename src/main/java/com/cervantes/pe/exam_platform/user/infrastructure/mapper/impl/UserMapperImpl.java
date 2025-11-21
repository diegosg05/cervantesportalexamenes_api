package com.cervantes.pe.exam_platform.user.infrastructure.mapper.impl;

import com.cervantes.pe.exam_platform.role.infrastructure.mapper.RoleMapper;
import com.cervantes.pe.exam_platform.user.domain.entity.User;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.CreateUserDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.RegisterStudentDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UserResponseDto;
import com.cervantes.pe.exam_platform.user.infrastructure.mapper.UserMapper;
import com.cervantes.pe.exam_platform.user.infrastructure.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;

    @Override
    public UserEntity fromUserToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .image(user.getImage())
                .enabled(user.getEnabled())
                .role(roleMapper.fromRoleToEntity(user.getRole()))
                .build();
    }

    @Override
    public User fromEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .lastname(userEntity.getLastname())
                .firstname(userEntity.getFirstname())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .enabled(userEntity.getEnabled())
                .image(userEntity.getImage())
                .role(roleMapper.fromEntityToRole(userEntity.getRole()))
                .build();
    }

    @Override
    public User fromRegisterDtoToUser(RegisterStudentDto dto) {
        return User.builder()
                .username(dto.username())
                .password(dto.password())
                .email(dto.email())
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .phone(dto.phone())
                .enabled(true)
                .image("user-default-profile.png")
                .build();
    }

    @Override
    public UserResponseDto fromUserToDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhone(),
                user.getImage(),
                user.getEnabled(),
                roleMapper.fromRoleToDto(user.getRole())
        );
    }

    @Override
    public User fromDtoToUser(CreateUserDto dto) {
        return User.builder()
                .username(dto.username())
                .password(dto.password())
                .email(dto.email())
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .phone(dto.phone())
                .enabled(dto.enabled())
                .image("user-default-profile.png")
                .build();
    }

}

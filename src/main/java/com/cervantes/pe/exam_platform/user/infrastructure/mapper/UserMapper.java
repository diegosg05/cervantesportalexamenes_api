package com.cervantes.pe.exam_platform.user.infrastructure.mapper;

import com.cervantes.pe.exam_platform.user.domain.entity.User;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.CreateUserDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.RegisterStudentDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UserResponseDto;
import com.cervantes.pe.exam_platform.user.infrastructure.persistence.UserEntity;

public interface UserMapper {
    UserEntity fromUserToEntity(User user);
    User fromEntityToUser(UserEntity userEntity);
    User fromRegisterDtoToUser(RegisterStudentDto dto);
    UserResponseDto fromUserToDto(User user);
    User fromDtoToUser(CreateUserDto dto);
}

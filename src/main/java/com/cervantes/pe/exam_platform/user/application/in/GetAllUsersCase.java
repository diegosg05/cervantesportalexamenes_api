package com.cervantes.pe.exam_platform.user.application.in;

import com.cervantes.pe.exam_platform.user.domain.entity.User;

import java.util.List;

public interface GetAllUsersCase {
    List<User> getAll();
}

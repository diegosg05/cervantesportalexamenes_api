package com.cervantes.pe.exam_platform.user.application.in;

import com.cervantes.pe.exam_platform.user.domain.entity.User;

public interface GetByUsernameCase {
    User getByUsername(String username);
}

package com.cervantes.pe.exam_platform.role.domain.entity;

import com.cervantes.pe.exam_platform.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;
    private List<User> users;
}

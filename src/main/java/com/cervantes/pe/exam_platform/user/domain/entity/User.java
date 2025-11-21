package com.cervantes.pe.exam_platform.user.domain.entity;

import com.cervantes.pe.exam_platform.role.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private Boolean enabled;
    private String image;
    private Role role;
}

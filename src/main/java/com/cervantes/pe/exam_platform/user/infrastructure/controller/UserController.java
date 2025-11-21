package com.cervantes.pe.exam_platform.user.infrastructure.controller;

import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.common.util.jwt.JwtUtil;
import com.cervantes.pe.exam_platform.role.application.in.GetRoleByIdCase;
import com.cervantes.pe.exam_platform.role.domain.exception.RoleNotFoundException;
import com.cervantes.pe.exam_platform.user.application.in.*;
import com.cervantes.pe.exam_platform.user.domain.exception.UserNotFoundException;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.CreateUserDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UpdatePasswordDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UpdateUserDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UserResponseDto;
import com.cervantes.pe.exam_platform.user.infrastructure.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CreateUserCase createUserCase;
    private final GetRoleByIdCase getRoleByIdCase;
    private final GetAllUsersCase getAllUsersCase;
    private final GetUsersByRole getUsersByRole;
    private final UpdateEnabledCase updateEnabledCase;
    private final UpdateUserCase updateUserCase;
    private final UpdatePasswordCase updatePasswordCase;
    private final GetByUsernameCase getByUsernameCase;
    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    @Value("${security.jwt.name}")
    String cookieName;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> saveUser(@RequestBody @Valid CreateUserDto createUserDto) {
        var roleOptional = getRoleByIdCase.getById(createUserDto.idRole());

        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException(createUserDto.idRole());
        }

        var user = userMapper.fromDtoToUser(createUserDto);
        user.setRole(roleOptional.get());

        var userSaved = createUserCase.save(user);
        var userDtoSaved = userMapper.fromUserToDto(userSaved);
        var apiResponse = new ApiResponse<>(
                userDtoSaved,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        var users = getAllUsersCase.getAll();
        var usersDto = users.stream()
                .map(userMapper::fromUserToDto)
                .toList();

        var apiResponse = new ApiResponse<>(
                usersDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsersByRole(@PathVariable Long id) {
        var roleOptional = getRoleByIdCase.getById(id);

        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException(id);
        }

        var users = getUsersByRole.getByRole(roleOptional.get());
        var usersDto = users.stream()
                .map(userMapper::fromUserToDto)
                .toList();

        var apiResponse = new ApiResponse<>(
                usersDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/enabled")
    public ResponseEntity<Void> updateEnabledUser(@PathVariable Long id, HttpServletRequest request) {
        String username = getUsernameFromCookie(request);

        if (username == null || username.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = getByUsernameCase.getByUsername(username);

        if (user.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        updateEnabledCase.updateEnabled(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
            @RequestBody @Valid UpdateUserDto updateUserDto,
            HttpServletRequest request
    ) {
        String username = getUsernameFromCookie(request);

        if (username == null || username.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = getByUsernameCase.getByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("El usuario no se ha encontrado");
        }

        user.setFirstname(updateUserDto.firstname());
        user.setLastname(updateUserDto.lastname());
        user.setPhone(updateUserDto.phone());
        user.setImage(updateUserDto.image());

        var userUpdate = updateUserCase.update(user);

        var userUpdateDto = userMapper.fromUserToDto(userUpdate);
        var apiResponse = new ApiResponse<>(
                userUpdateDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @RequestBody @Valid UpdatePasswordDto updatePasswordDto,
            HttpServletRequest request
    ) {
        String username = getUsernameFromCookie(request);

        if (username == null || username.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        updatePasswordCase.updatePassword(username, updatePasswordDto.oldPassword(), updatePasswordDto.newPassword());

        return ResponseEntity.noContent().build();
    }

    private String getUsernameFromCookie(HttpServletRequest request) {
        String token = null;
        var cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (var cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null || token.isBlank()) {
            return null;
        }

        return jwtUtil.extractUsername(token);
    }
}

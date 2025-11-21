package com.cervantes.pe.exam_platform.user.infrastructure.controller;

import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.common.util.jwt.JwtUtil;
import com.cervantes.pe.exam_platform.role.application.in.GetRoleByIdCase;
import com.cervantes.pe.exam_platform.role.domain.exception.RoleNotFoundException;
import com.cervantes.pe.exam_platform.user.application.in.CreateUserCase;
import com.cervantes.pe.exam_platform.user.application.in.GetByUsernameCase;
import com.cervantes.pe.exam_platform.user.application.in.LoginUserCase;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.LoginUserDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.RegisterStudentDto;
import com.cervantes.pe.exam_platform.user.infrastructure.dto.UserResponseDto;
import com.cervantes.pe.exam_platform.user.infrastructure.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserCase createUserCase;
    private final GetByUsernameCase getByUsernameCase;
    private final GetRoleByIdCase getRoleByIdCase;
    private final LoginUserCase loginUser;

    private final UserMapper userMapper;

    private final JwtUtil jwtUtil;

    @Value("${security.jwt.name}")
    String cookieAccessTokenName;

    @Value("${security.jwt.refresh-token.name}")
    String cookieRefreshTokenName;

    @Value("${security.jwt.expiration}")
    private long accessExpiration;

    @Value("${security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerStudent(
            @RequestBody @Valid RegisterStudentDto registerStudentDto
    ) {
        var student = userMapper.fromRegisterDtoToUser(registerStudentDto);
        var rolOptional = getRoleByIdCase.getById(2L);

        if (rolOptional.isEmpty()) {
            throw new RoleNotFoundException(2L);
        }

        student.setRole(rolOptional.get());
        var studentSaved = createUserCase.save(student);
        var studentEntitySaved = userMapper.fromUserToEntity(studentSaved);

        var accessToken = jwtUtil.generateToken(studentEntitySaved);
        var refreshToken = jwtUtil.generateRefreshToken(studentEntitySaved);

        var cookieAccess = ResponseCookie.from(cookieAccessTokenName, accessToken)
                .sameSite("Strict")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(accessExpiration)
                .build();

        var cookieRefresh = ResponseCookie.from(cookieRefreshTokenName, refreshToken)
                .sameSite("Strict")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(refreshExpiration)
                .build();

        var studentDtoSaved = userMapper.fromUserToDto(studentSaved);

        var apiResponse = new ApiResponse<>(
                studentDtoSaved,
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, cookieAccess.toString())
                .header(HttpHeaders.SET_COOKIE, cookieRefresh.toString())
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> loginUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        var user = loginUser.login(loginUserDto.username(), loginUserDto.password());
        var userEntity = userMapper.fromUserToEntity(user);

        var accessToken = jwtUtil.generateToken(userEntity);
        var refreshToken = jwtUtil.generateRefreshToken(userEntity);

        var cookieAccess = ResponseCookie.from(cookieAccessTokenName, accessToken)
                .sameSite("Strict")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(accessExpiration)
                .build();

        var cookieRefresh = ResponseCookie.from(cookieRefreshTokenName, refreshToken)
                .sameSite("Strict")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(refreshExpiration)
                .build();

        var userDto = userMapper.fromUserToDto(user);
        var apiResponse = new ApiResponse<>(
                userDto,
                null
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookieAccess.toString())
                .header(HttpHeaders.SET_COOKIE, cookieRefresh.toString())
                .body(apiResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request) {
        String refreshToken = null;

        var cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        for (var cookie : cookies) {
            if (cookie.getName().equals(cookieRefreshTokenName)) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null || refreshToken.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var username = jwtUtil.extractUsername(refreshToken);
        var user = getByUsernameCase.getByUsername(username);
        var userEntity = userMapper.fromUserToEntity(user);

        if (!jwtUtil.isTokenValid(refreshToken, userEntity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var newAccessToken = jwtUtil.generateToken(userMapper.fromUserToEntity(user));

        ResponseCookie newAccessCookie = ResponseCookie.from(cookieAccessTokenName, newAccessToken)
                .httpOnly(true)
                .path("/")
                .secure(false)
                .sameSite("Strict")
                .maxAge(accessExpiration)
                .build();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, newAccessCookie.toString())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        ResponseCookie clearAccess = ResponseCookie.from(cookieAccessTokenName, "")
                .path("/")
                .maxAge(0)
                .build();

        ResponseCookie clearRefresh = ResponseCookie.from(cookieRefreshTokenName, "")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, clearAccess.toString())
                .header(HttpHeaders.SET_COOKIE, clearRefresh.toString())
                .build();
    }

}

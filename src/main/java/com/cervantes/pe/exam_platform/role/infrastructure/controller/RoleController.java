package com.cervantes.pe.exam_platform.role.infrastructure.controller;

import com.cervantes.pe.exam_platform.common.response.ApiResponse;
import com.cervantes.pe.exam_platform.role.application.in.*;
import com.cervantes.pe.exam_platform.role.domain.exception.RoleNotFoundException;
import com.cervantes.pe.exam_platform.role.infrastructure.dto.RoleDto;
import com.cervantes.pe.exam_platform.role.infrastructure.mapper.RoleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final CreateRoleCase createRoleCase;
    private final GetRoleByIdCase getRoleByIdCase;
    private final GetAllRolesCase getAllRolesCase;
    private final UpdateRoleCase updateRoleCase;
    private final DeleteRoleCase deleteRoleCase;

    private final RoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> saveRole(@RequestBody @Valid RoleDto roleDto) {
        var roleSaved = createRoleCase.save(roleMapper.fromDtoToRole(roleDto));
        var roleDtoSaved = roleMapper.fromRoleToDto(roleSaved);
        var apiResponse = new ApiResponse<>(
                roleDtoSaved,
                null
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(roleSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Long id) {
        var roleOptional = getRoleByIdCase.getById(id);

        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException(id);
        }

        var roleDto = roleMapper.fromRoleToDto(roleOptional.get());
        var apiResponse = new ApiResponse<>(
                roleDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles() {
        var roles = getAllRolesCase.getAll();
        var rolesDto = roles.stream()
                .map(roleMapper::fromRoleToDto)
                .toList();

        var apiResponse = new ApiResponse<>(
                rolesDto,
                null
        );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateRole(@RequestBody @Valid RoleDto roleDto) {
        if (roleIsEmpty(roleDto.id())) {
            throw new RoleNotFoundException(roleDto.id());
        }

        updateRoleCase.update(roleMapper.fromDtoToRole(roleDto));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (roleIsEmpty(id)) {
            throw new RoleNotFoundException(id);
        }

        deleteRoleCase.delete(id);

        return ResponseEntity.noContent().build();
    }

    private boolean roleIsEmpty(Long id) {
        var roleOptional = getRoleByIdCase.getById(id);
        return roleOptional.isEmpty();
    }
}

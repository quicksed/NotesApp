package com.notes.service;

import com.notes.dto.role.RoleDto;

import java.util.Set;

public interface RoleService {

    RoleDto getRoleById(Long id);

    Set<RoleDto> getAllRoles();
}

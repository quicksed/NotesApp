package com.notes.dto.mapper;

import com.notes.dto.role.RoleDto;
import com.notes.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());

        return roleDto;
    }

    public Set<RoleDto> toDto(List<Role> roles) {
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }
}

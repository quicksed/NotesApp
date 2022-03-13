package com.notes.service.impl;

import com.notes.dto.mapper.RoleMapper;
import com.notes.dto.role.RoleDto;
import com.notes.entity.Role;
import com.notes.exception.NotFoundException;
import com.notes.repository.RoleRepository;
import com.notes.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));

        return roleMapper.toDto(role);
    }

    @Override
    public Set<RoleDto> getAllRoles() {
        return roleMapper.toDto(roleRepository.findAll());
    }
}

package com.notes.dto.mapper;

import com.notes.dto.user.UserCreateDto;
import com.notes.dto.user.UserDto;
import com.notes.entity.Role;
import com.notes.entity.User;
import com.notes.exception.NotFoundException;
import com.notes.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    public UserDto entityToUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRegistrationDate(userDto.getRegistrationDate());
        userDto.setRoles(user.getRoles());

        return userDto;
    }

    public User userCreateDtoToEntity(UserCreateDto userCreateDto) {

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("ROLE_USER not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        User user = new User();
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());
        user.setRoles(roles);

        return user;
    }
}

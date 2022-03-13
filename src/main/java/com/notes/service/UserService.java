package com.notes.service;

import com.notes.dto.user.UserCreateDto;
import com.notes.dto.user.UserDto;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto getUserByEmail(String email);

    UserDto createUser(UserCreateDto userCreateDto);

    void deleteUser(Long id);
}

package com.notes.service.impl;

import com.notes.dto.mapper.UserMapper;
import com.notes.dto.user.UserCreateDto;
import com.notes.dto.user.UserDto;
import com.notes.entity.User;
import com.notes.exception.EmailAlreadyTakenException;
import com.notes.exception.NotFoundException;
import com.notes.exception.NotValidEmailException;
import com.notes.exception.PasswordsNotEqualsException;
import com.notes.repository.UserRepository;
import com.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", id)));

        return userMapper.entityToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));

        return userMapper.entityToUserDto(user);
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {

        if (!isValidEmail(userCreateDto.getEmail())) {
            throw new NotValidEmailException();
        }

        //Проверка на соответствие пароля и повторенного пароля
        if (!userCreateDto.getPassword().equals(userCreateDto.getRepeatedPassword())) {
            throw new PasswordsNotEqualsException();
        }

        //Проверка на занятость email
        Optional<User> userForDbForCheck = userRepository.findByEmail(userCreateDto.getEmail());
        if (userForDbForCheck.isPresent()) {
            throw new EmailAlreadyTakenException();
        }

        User user = userMapper.userCreateDtoToEntity(userCreateDto);
        userRepository.save(user);

        return userMapper.entityToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", id)));

        userRepository.delete(user);
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}

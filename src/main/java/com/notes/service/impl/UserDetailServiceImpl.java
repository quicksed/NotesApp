package com.notes.service.impl;

import com.notes.entity.Role;
import com.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.notes.entity.User userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email '%s' not found", email)));

        return User.builder()
                .username(userInfo.getEmail())
                .password(userInfo.getPassword())
                .roles(String.valueOf(userInfo.getRoles().stream().map(Role::getName)))
                .build();
    }
}

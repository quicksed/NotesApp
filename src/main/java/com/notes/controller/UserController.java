package com.notes.controller;

import com.notes.dto.user.UserCreateDto;
import com.notes.dto.user.UserDto;
import com.notes.entity.Role;
import com.notes.service.NoteService;
import com.notes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NoteService noteService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "/registration", consumes = "application/x-www-form-urlencoded")
    public String registerUser(@Valid UserCreateDto userCreateDto, Model model) {
        userService.createUser(userCreateDto);
        UserDto user = userService.getUserByEmail(userCreateDto.getEmail());

        //Аутентификация после регистрации
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:home";
    }
}

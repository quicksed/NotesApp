package com.notes.dto.user;

import com.notes.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String email;

    private String password;

    private ZonedDateTime registrationDate;

    private Set<Role> roles;
}

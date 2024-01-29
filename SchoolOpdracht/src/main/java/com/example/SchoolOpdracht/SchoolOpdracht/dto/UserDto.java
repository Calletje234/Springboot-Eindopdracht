package com.example.SchoolOpdracht.SchoolOpdracht.dto;


import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;

import java.util.Collection;

public class UserDto {
    public String username;
    public String password;
    public Collection<Role> roles;
}

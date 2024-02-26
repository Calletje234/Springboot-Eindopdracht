package com.example.SchoolOpdracht.SchoolOpdracht.dto;


import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;

import java.util.Collection;

public class UserDto {
    public String username;
    public String password;
    public String userType;
    public Long userTypeId;
    public Collection<Role> roles;
}

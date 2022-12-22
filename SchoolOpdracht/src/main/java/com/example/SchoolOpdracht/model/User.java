package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import com.example.SchoolOpdracht.model.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@Getter @Setter
public class User {
    @Id
    private String Username;
    private String Password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}

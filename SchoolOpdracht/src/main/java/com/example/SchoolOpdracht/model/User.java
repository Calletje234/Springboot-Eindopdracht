package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@Getter @Setter
public class User {

    @Id
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}

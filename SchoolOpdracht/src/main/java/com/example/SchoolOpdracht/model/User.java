package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="users")
@Getter @Setter
public class User {

    @Id
    private String username;
    private String password;

    @ManyToMany
    private Collection<Role> roles;
}

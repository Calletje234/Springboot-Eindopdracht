package com.example.SchoolOpdracht.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@Getter @Setter
public class User {

    @Id
    private Long id;

    private String username;
    private String password;

    private String userType;
    private Long userTypeId;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}

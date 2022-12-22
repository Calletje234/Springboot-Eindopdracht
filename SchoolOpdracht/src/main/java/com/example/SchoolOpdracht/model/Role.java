package com.example.SchoolOpdracht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role {
    @Id
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Collection<Role> roles;
}

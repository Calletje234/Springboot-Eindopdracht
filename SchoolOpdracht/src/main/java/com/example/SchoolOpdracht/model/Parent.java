package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Parents")
@Getter @Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String countryOfOrigin;
    private String spokenLanguage;

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    @OneToOne(mappedBy = "parent")
    private Task task;
}

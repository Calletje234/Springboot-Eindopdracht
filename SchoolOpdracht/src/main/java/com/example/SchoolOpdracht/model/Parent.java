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

    // Variables not mapped by other services
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

    // default constructor
    public Parent() {}

    public Parent(String firstName,
                  String lastName,
                  String phoneNumber,
                  String address,
                  String countryOfOrigin,
                  String spokenLanguage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.countryOfOrigin = countryOfOrigin;
        this.spokenLanguage = spokenLanguage;
    }
}

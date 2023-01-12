package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Parents")
@Getter @Setter
public class Parent {
    @Id
    @GeneratedValue
    private Long parentId;
    private Long childId;
    private Long taskId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String countryOfOrigin;
    private String spokenLanguage;

    // @OneToMany(mappedBy = "mother")


}

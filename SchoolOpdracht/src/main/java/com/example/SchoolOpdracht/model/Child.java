package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "Childs")
@Getter @Setter
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String countryOfOrigin;
    private String spokenLanguage;
    private ArrayList<String> Allergies;
    private LocalDate startingDate;

    @OneToOne(mappedBy = "child")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

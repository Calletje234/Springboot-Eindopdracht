package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "Childs")
@Getter @Setter
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    // Variables not mapped by other services
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
    @JoinColumn(name = "parentId")
    private Parent parent;

    public Child(String firstName,
                 String lastName,
                 LocalDate dob,
                 String address,
                 String countryOfOrigin,
                 String spokenLanguage,
                 ArrayList<String> allergies,
                 LocalDate startingDate,
                 Parent parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.countryOfOrigin = countryOfOrigin;
        this.spokenLanguage = spokenLanguage;
        this.Allergies = allergies;
        this.startingDate = startingDate;
        this.parent = parent;
    }
}

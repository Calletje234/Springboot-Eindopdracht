package com.example.SchoolOpdracht.SchoolOpdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
@Getter @Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    // Variables not mapped by other services
    private String firstName;
    private String lastName;
    private int taskAmount;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Task> tasks;

    @OneToMany(mappedBy = "afwezigTeacher")
    private List<Afwezig> afwezigheid;

    // default constructor
    public Teacher() {}

    public Teacher(String firstName, String lastName, int taskAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskAmount = taskAmount;
    }
 }

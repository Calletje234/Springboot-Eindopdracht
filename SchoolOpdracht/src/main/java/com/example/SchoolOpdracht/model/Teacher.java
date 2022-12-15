package com.example.SchoolOpdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Teachers")
@Getter @Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String firstName;
    private String lastName;
    private int taskAmount;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Task> tasks;

}

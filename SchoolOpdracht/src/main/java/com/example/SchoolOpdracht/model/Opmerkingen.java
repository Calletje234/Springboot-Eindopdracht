package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Opmerkingen")
@Getter @Setter
public class Opmerkingen {
    @Id
    @GeneratedValue
    private Long opmerkingenId;
    private LocalDate dateOfContact;
    private String opmerking;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task newTask;
}

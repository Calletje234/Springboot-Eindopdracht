package com.example.SchoolOpdracht.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Opmerkingen")
@Getter @Setter
public class Opmerking {
    @Id
    @GeneratedValue
    private Long opmerkingenId;

    // Variables not mapped by other services
    private LocalDate dateOfContact;
    private String opmerking;

    @ManyToOne
    @JoinColumn(name = "taskId")
    Task task;

    // default constructor
    public Opmerking() {}

    public Opmerking(Long opmerkingenId, LocalDate dateOfContact, String opmerking, Task task) {
        this.opmerkingenId = opmerkingenId;
        this.dateOfContact = dateOfContact;
        this.opmerking = opmerking;
        this.task = task;
    }
}

package com.example.SchoolOpdracht.SchoolOpdracht.model;


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

    // Variables not mapped by other services
    private LocalDate dateOfContact;
    private String opmerking;

    @ManyToOne
    @JoinColumn(name = "taskId")
    Task task;

    // default constructor
    public Opmerkingen() {}

    public Opmerkingen(Long opmerkingenId, LocalDate dateOfContact, String opmerking, Task task) {
        this.opmerkingenId = opmerkingenId;
        this.dateOfContact = dateOfContact;
        this.opmerking = opmerking;
        this.task = task;
    }
}

package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Opmerkingen")
@Getter @Setter
@NoArgsConstructor
public class Opmerkingen {
    @Id
    @GeneratedValue
    private Long opmerkingenId;

    // Variables not mapped by other services
    private LocalDate dateOfContact;
    private String opmerking;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task newTask;

    public Opmerkingen(LocalDate dateOfContact, String opmerking) {
        this.dateOfContact = dateOfContact;
        this.opmerking = opmerking;
    }
}

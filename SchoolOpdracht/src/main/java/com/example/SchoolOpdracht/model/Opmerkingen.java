package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Opmerkingen")
@Getter @Setter
public class Opmerkingen {
    @Id
    @GeneratedValue
    private Long opmerkingenId;
    private Long taskId;
    private LocalDate dateOfContact;
    private String opmerking;
}

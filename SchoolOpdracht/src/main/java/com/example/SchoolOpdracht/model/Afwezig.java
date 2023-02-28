package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "Afwezig")
@Getter @Setter
public class Afwezig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afwezigId;

    // Variables not mapped by other services
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher afwezigTeacher;

    // default constructor
    public Afwezig() {}

    public Afwezig(String reason, LocalDate startDate, LocalDate endDate) {
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

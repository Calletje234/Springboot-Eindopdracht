package com.example.SchoolOpdracht.SchoolOpdracht.model;


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
    @JoinColumn(name = "teacherId")
    Teacher afwezigTeacher;

    // default constructor
    public Afwezig() {}

    public Afwezig(Long afwezigId, String reason, LocalDate startDate, LocalDate endDate, Teacher afwezigTeacher) {
        this.afwezigId = afwezigId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.afwezigTeacher = afwezigTeacher;
    }
}

package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "Afwezig")
@Getter @Setter
@NoArgsConstructor
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

    public Afwezig(Long afwezigId,String reason, LocalDate startDate, LocalDate endDate, Teacher teacher) {
        this.afwezigId = afwezigId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.afwezigTeacher = teacher;
    }
}

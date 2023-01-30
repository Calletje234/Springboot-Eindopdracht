package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Afwezig")
@Getter @Setter
public class Afwezig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long afwezigId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher afwezigTeacher;
}

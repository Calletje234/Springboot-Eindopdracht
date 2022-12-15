package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Afwezig")
@Getter @Setter
public class Afwezig {
    @Id
    @GeneratedValue
    private Long afwezigId;
    private Long teacherId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
}

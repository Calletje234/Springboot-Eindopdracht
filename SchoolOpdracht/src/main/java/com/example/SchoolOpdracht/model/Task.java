package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tasks")
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    // Variables not mapped by other services
    private String status;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToMany(mappedBy = "newTask")
    private List<Opmerkingen> opmerkingen;

    // default constructor
    public Task() {}

    public Task(String status, LocalDate dueDate) {
        this.status = status;
        this.dueDate = dueDate;
    }
}

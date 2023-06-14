package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tasks")
@Getter @Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    // Variables not mapped by other services
    private String status;
    private LocalDate dueDate;
    private String taskDescription;
    private boolean assigned;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "childId")
    private Child child;

    @OneToMany(mappedBy = "newTask")
    private List<Opmerkingen> opmerkingen;

    public Task(String status, LocalDate dueDate, boolean assigned) {
        this.status = status;
        this.dueDate = dueDate;
        this.assigned = assigned;
    }
}

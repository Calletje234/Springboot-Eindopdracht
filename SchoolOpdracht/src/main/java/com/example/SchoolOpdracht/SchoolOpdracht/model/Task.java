package com.example.SchoolOpdracht.SchoolOpdracht.model;


import com.example.SchoolOpdracht.SchoolOpdracht.Enum.TaskStatus;
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
    private TaskStatus status;
    private LocalDate dueDate;
    private Boolean assigned;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "childId")
    private Child child;

    @OneToMany(mappedBy = "task")
    private List<Opmerking> opmerking;

    public Task(TaskStatus status, LocalDate dueDate, boolean assigned) {
        this.status = status;
        this.dueDate = dueDate;
        this.assigned = assigned;
    }
}

package com.example.SchoolOpdracht.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tasks")
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private Long childId;
    private Long TeacherId;
    private Long ParentId;
    private String Status;
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
}

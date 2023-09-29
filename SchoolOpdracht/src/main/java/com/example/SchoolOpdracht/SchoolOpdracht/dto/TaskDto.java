package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskDto {
    @NotNull
    public LocalDate dueDate;

    @NotNull
    public Long childId;

    public Long teacherId;
    public String status;
    public Boolean assigned;
}

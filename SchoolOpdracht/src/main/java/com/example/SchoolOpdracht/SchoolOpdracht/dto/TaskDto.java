package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import com.example.SchoolOpdracht.SchoolOpdracht.Enum.TaskStatus;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskDto {
    @NotNull
    public LocalDate dueDate;
    @NotNull
    public Long childId;
    public Long teacherId;
    public TaskStatus status;
    public Boolean assigned;
}

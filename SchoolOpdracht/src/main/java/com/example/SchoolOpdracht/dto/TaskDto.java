package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskDto {
    @Future
    public LocalDate dueDate;

    @NotNull
    public Long childId;

    public String taskDescription;

    public Long teacherId = null;

    public String status;
}

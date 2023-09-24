package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskDto {
    @NotBlank
    public LocalDate dueDate;

    @NotNull
    public Long childId;

    public Long teacherId = null;
    public String status;
    public Boolean assigned;
}

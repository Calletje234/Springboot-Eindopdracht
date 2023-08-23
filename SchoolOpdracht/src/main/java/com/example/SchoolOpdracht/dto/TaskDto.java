package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TaskDto {
    @NotBlank
    public LocalDate dueDate;

    @NotBlank
    public Long childId;

    public Long teacherId = null;
    public String status;
    public boolean assigned;
}

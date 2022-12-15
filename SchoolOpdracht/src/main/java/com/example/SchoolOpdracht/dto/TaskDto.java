package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class TaskDto {
    @NotBlank
    public LocalDate dueDate;
    @NotBlank
    public Long childId;
    @NotBlank
    public Long parentId;
    public Long teacherId;
}

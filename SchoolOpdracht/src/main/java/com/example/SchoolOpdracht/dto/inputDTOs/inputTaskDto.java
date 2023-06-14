package com.example.SchoolOpdracht.dto.inputDTOs;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class inputTaskDto {
    @Future
    public LocalDate dueDate;
    @NotNull
    public Long childId;
    @NotBlank
    public String taskDescription;
    public Long teacherId;
    public String status;
}

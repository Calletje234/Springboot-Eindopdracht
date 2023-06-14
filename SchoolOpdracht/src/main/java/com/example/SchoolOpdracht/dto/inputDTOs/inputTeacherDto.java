package com.example.SchoolOpdracht.dto.inputDTOs;

import com.example.SchoolOpdracht.model.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class inputTeacherDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @PositiveOrZero
    public int taskAmount;
    public List<Task> tasks;
}


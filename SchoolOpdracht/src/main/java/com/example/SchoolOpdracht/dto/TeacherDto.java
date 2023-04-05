package com.example.SchoolOpdracht.dto;

import java.util.ArrayList;

import com.example.SchoolOpdracht.model.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class TeacherDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @PositiveOrZero
    public int taskAmount;
    public ArrayList<Task> tasks;
}

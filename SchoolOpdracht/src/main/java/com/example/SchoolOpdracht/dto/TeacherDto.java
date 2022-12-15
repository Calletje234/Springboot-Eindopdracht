package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class TeacherDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    public int taskAmount;
}

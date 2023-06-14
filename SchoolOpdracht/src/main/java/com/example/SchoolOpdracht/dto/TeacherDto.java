package com.example.SchoolOpdracht.dto;

import java.util.List;

import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.model.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public record TeacherDto(Long teacherID,
                         String firstName,
                         String lastName,
                         int taskAmount,
                         List<Task> taskList,
                         List<Afwezig> afwezigList) {
}

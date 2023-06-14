package com.example.SchoolOpdracht.dto;

import com.example.SchoolOpdracht.model.Opmerkingen;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TaskDto(Long taskId,
                      String status,
                      LocalDate dueDate,
                      String taskDescription,
                      boolean assigned,
                      Long teacherId,
                      Long childId,
                      List<Opmerkingen> opmerkingList) {
}

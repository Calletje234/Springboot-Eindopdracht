package com.example.SchoolOpdracht.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

public record AfwezigDto(Long afwezigId,
                         Long teacherId,
                         String reason,
                         LocalDate startDate,
                         LocalDate endDate) {
}

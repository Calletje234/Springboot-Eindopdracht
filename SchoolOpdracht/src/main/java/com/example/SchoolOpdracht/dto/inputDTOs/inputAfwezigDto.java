package com.example.SchoolOpdracht.dto.inputDTOs;

import net.bytebuddy.asm.Advice;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class inputAfwezigDto {
    @NotBlank
    Long teacherId;
    @NotBlank
    String reason;
    @NotBlank
    LocalDate startDate;
    @Future
    @NotBlank
    LocalDate endDate;
}

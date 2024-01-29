package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AfwezigDto {
    @NotNull
    public Long teacherId;
    @NotBlank
    public String reason;
    @NotBlank
    public LocalDate startDate;
    @Future
    public LocalDate endDate;
}

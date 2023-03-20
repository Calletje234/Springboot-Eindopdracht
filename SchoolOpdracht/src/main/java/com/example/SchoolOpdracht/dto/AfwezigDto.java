package com.example.SchoolOpdracht.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

public class AfwezigDto {
    @NotBlank
    public Long teacherId;
    @NotBlank
    public String reason;
    @NotBlank
    public LocalDate startDate;
    @Future
    public LocalDate endDate;
}

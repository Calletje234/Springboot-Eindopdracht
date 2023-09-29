package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class OpmerkingenDto {
    @NotNull
    public Long taskId;
    @PastOrPresent
    public LocalDate dateOfContact;
    @NotBlank
    public String opmerking;
}

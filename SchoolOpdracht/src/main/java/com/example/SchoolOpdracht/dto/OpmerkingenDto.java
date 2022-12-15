package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class OpmerkingenDto {
    @NotBlank
    public Long taskId;
    @PastOrPresent
    public LocalDate dateOfContact;
    @NotBlank
    public String opmerking;
}

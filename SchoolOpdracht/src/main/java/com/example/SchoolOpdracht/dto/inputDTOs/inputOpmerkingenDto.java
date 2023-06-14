package com.example.SchoolOpdracht.dto.inputDTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class inputOpmerkingenDto {
    @NotBlank
    public Long taskId;
    @NotBlank
    @PastOrPresent
    public LocalDate dateOfContact;
    @NotBlank
    public String opmerking;
}

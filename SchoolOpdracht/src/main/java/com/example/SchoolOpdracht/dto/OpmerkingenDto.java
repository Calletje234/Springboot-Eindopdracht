package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record OpmerkingenDto(Long opmerkingId,
                             LocalDate dateOfContact,
                             String opmerking,
                             Long taskId) {
}

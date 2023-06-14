package com.example.SchoolOpdracht.dto.inputDTOs;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;

public class inputChildDto {
    @NotBlank
    public Long parentId;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @Past
    public LocalDate dob;
    @NotBlank
    public String address;
    public String countryOfOrigin;
    @NotBlank
    public String spokenLanguage;
    public ArrayList<String> Allergies;
    @NotBlank
    @Future
    public LocalDate startingDate;
}

package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;

public class ChildDto {
    @NotNull
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
    @Future
    public LocalDate startingDate;
}

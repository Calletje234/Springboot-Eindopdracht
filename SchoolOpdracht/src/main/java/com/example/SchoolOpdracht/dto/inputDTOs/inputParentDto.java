package com.example.SchoolOpdracht.dto.inputDTOs;

import com.example.SchoolOpdracht.model.Child;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class inputParentDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    public String phoneNumber;
    @NotBlank
    public String address;
    public String countryOfOrigin;
    @NotBlank
    public String spokenLanguage;
}

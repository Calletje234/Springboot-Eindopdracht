package com.example.SchoolOpdracht.dto;

import com.example.SchoolOpdracht.model.Child;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ParentDto {
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
    public List<Child> childList;
}

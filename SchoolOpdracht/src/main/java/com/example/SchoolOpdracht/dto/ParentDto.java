package com.example.SchoolOpdracht.dto;

import javax.validation.constraints.NotBlank;

public class ParentDto {
    public Long childId;
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

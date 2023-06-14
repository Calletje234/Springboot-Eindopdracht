package com.example.SchoolOpdracht.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public record ChildDto(Long childId,
                       Long parentId,
                       String firstName,
                       String lastName,
                       LocalDate dob,
                       String address,
                       String countryOfOrigin,
                       String spokenLanguage,
                       ArrayList<String> allergies,
                       LocalDate startingDate) {
}

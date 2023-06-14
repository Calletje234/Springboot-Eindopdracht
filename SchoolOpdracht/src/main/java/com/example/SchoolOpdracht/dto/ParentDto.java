package com.example.SchoolOpdracht.dto;

import com.example.SchoolOpdracht.model.Child;
import java.util.List;

public record ParentDto(Long parentId,
                        String firstName,
                        String lastName,
                        String phoneNumber,
                        String address,
                        String countryOfOrigin,
                        String spokenLanguage,
                        List<Child> childList) {

}

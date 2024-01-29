package com.example.SchoolOpdracht.SchoolOpdracht.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FileDto {
    @NotNull
    public String fileName;

   @NotNull
    public String fileType;

   @NotNull
    public byte[] fileData;

   @NotNull(message = "parentId mag niet null zijn")
   @NotEmpty(message = "parentId mag niet leeg zijn")
    public Long parentId;

   @NotNull(message = "parentType mag niet null zijn")
   @NotEmpty(message = "parentType mag niet leeg zijn")
    public String parentType;
}

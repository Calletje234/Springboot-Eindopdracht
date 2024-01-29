package com.example.SchoolOpdracht.SchoolOpdracht.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Files")
@Getter @Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    private String parentType;
    private Long parentId;

    // default constructor
    public File() {}

    public File(String fileName, String fileType, byte[] data, String parentType, Long parentId) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.parentType = parentType;
        this.parentId = parentId;
    }
}

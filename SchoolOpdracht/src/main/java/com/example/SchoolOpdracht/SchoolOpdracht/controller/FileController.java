package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.service.FileService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService service;

    public FileController(FileService f) {this.service = f;}

    @GetMapping("")
    public ResponseEntity<Iterable<FileDto>> getAllFiles() {
        return ResponseEntity.ok(service.getAllFiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFileById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createFile(@Valid @RequestBody FileDto fileDto, BindingResult br) {
        Long createdId = service.createFile(fileDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/afwezig/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("File uploaded");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FileDto> deleteFileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteFileById(id));
    }
}

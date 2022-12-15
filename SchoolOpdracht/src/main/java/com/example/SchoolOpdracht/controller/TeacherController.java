package com.example.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.dto.TeacherDto;
import com.example.SchoolOpdracht.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService s) {
        this.service = s;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<TeacherDto>> getTeachers() {
        return ResponseEntity.ok(service.getTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeacherById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br){
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createTeacher(teacherDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/teachers/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Teacher created!");
    }
}

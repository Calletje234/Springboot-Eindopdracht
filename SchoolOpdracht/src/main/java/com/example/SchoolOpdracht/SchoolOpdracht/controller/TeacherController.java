package com.example.SchoolOpdracht.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TeacherDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.TeacherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksOfTeacherById(@PathVariable Long id, @RequestParam String taskStatus) {
        return ResponseEntity.ok(service.getTasksWithStatus(id, taskStatus));
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileDto>> getAssociatedFiles(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFileByTeacher(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br){
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createTeacher(teacherDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/teachers/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Teacher created!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDto> removeTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTeacherById(id));
    }

    @PutMapping("/{id}/taskamount")
    public ResponseEntity addTaskAmount(@Valid @RequestBody Long taskId,
                                        @PathVariable Long id,
                                        BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.addTaskToTeacher(id, taskId));
    }

    @PutMapping("/{id}/firstName")
    public ResponseEntity changeTeacherFirstName(@Valid @RequestBody TeacherDto teacherDto,
                                                 @PathVariable Long id,
                                                 BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeFirstName(id, teacherDto));
    }

    @PutMapping("/{id}/lastName")
    public ResponseEntity changeTeacherLastName(@Valid @RequestBody TeacherDto teacherDto,
                                                @PathVariable Long id,
                                                BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeLastName(id, teacherDto));
    }
}

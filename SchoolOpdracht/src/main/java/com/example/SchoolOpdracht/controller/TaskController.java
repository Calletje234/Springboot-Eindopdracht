package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService t) {
        this.service = t;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<TaskDto>> getAllTasks() {return ResponseEntity.ok(service.getAllTask());}

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskDto taskDto, BindingResult br) {
        if(br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createTask(taskDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/tasks/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Task Created");
    }

    @PutMapping("/changeTeacher/{id}")
    public ResponseEntity changeTeacher(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeAssignedTeacher(id, taskDto));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity updateTaskStatus(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeTaskStatus(id, taskDto));
    }

    @PutMapping("/changeParent/{id}")
    public ResponseEntity changeParent(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeParentId(id, taskDto));
    }

    @PutMapping("/updateDueDate/{id}")
    public ResponseEntity updateDueDate(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeDueDate(id, taskDto));
    }

    @DeleteMapping("/${id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTaskById(id));
    }
}

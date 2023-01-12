package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Task;
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
    private Util util;

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
            return new ResponseEntity(util.createErrorMessage(br).toString(), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createTask(taskDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/tasks/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Task Created");
    }

    @PutMapping("/changeTeacher/{id}")
    public ResponseEntity<TaskDto> changeTeacher(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id) {
        service.changeAssignedTeacher(id, taskDto);
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<TaskDto> updateTaskStatus(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id ) {
        service.changeTaskStatus(id, taskDto);
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PutMapping("/changeParent/{id}")
    public ResponseEntity<TaskDto> changeParent(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id) {
        service.changeParentId(id, taskDto);
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PutMapping("/updateDueDate/{id}")
    public ResponseEntity<TaskDto> updateDueDate(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id) {
        service.changeDueDate(id, taskDto);
        return ResponseEntity.ok(service.getTaskById(id));
    }
}

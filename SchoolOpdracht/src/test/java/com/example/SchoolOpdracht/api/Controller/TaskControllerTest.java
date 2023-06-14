package com.example.SchoolOpdracht.api.Controller;


import com.example.SchoolOpdracht.controller.TaskController;
import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;
    private TaskController taskController;

    @Test
    public void testCreateTask_InvalidInput() {
        TaskDto taskDto = new TaskDto();

        BindingResult br = new MapBindingResult(Collections.emptyMap(), "");
        br.addError(new ObjectError("TaskDto", "Title is required."));
        br.addError(new ObjectError("TaskDto", "Due date must be in the future."));

        ResponseEntity<String> response = taskController.createTask(taskDto, br);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Title is required. Due date must be in the future.", response.getBody());
    }

    @Test
    public void testGetAllTasks() {
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto());
        tasks.add(new TaskDto());

        when(taskService.getAllTask()).thenReturn(tasks);

        ResponseEntity<Iterable<TaskDto>> response = taskController.getAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks.size(), ((List<TaskDto>)response.getBody()).size());
    }

    @Test
    public void testGetTaskById() {
        Long id = 1L;
        TaskDto taskDto = new TaskDto();

        when(taskService.getTaskById(id)).thenReturn(taskDto);

        ResponseEntity<TaskDto> response = taskController.getTaskById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskDto, response.getBody());
    }

    @Test
    public void testCheckIfTaskIsOverdue() {
        Long id = 1L;
        Boolean overdue = true;

        when(taskService.checkIfTaskIsOverdue(id)).thenReturn(overdue);

        ResponseEntity<Boolean> response = taskController.checkIfTaskIsOverdue(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(overdue, response.getBody());
    }

    @Test
    public void testGetAmountOfDays() {
        Long id = 1L;
        Integer days = 3;

        when(taskService.getDayBeforeOverdue(id)).thenReturn(days);

        ResponseEntity<Integer> response = taskController.getAmountOfDays(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(days, response.getBody());
    }
}
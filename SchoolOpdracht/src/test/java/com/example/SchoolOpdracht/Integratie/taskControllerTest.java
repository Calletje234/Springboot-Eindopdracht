package com.example.SchoolOpdracht.Integratie;

import java.time.LocalDate;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.controller.TaskController;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.service.TaskService;


@WebMvcTest(TaskController.class)

@RunWith(SpringRunner.class)
class taskControllerTest {

    Task task1;
    Task task2;
    Task task3;

    TaskDto taskDto1;
    TaskDto taskDto2;
    TaskDto taskDto3;

    TaskDto inputTaskDto1;
    TaskDto inputTaskDto2;
    TaskDto inputTaskDto3;

    String pickedUp = "Picked up";
    String newTask = "New";
    String finished = "Finished";

    LocalDate date1 = LocalDate.of(2023, 5, 10);
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        task1 = new Task(pickedUp, date1, true);
        task2 = new Task(newTask, date1, false);
        task3 = new Task(finished, date1, false);

        taskDto1 = createDto(date1, pickedUp, true);
        taskDto2 = createDto(date1, newTask, false);
        taskDto3 = createDto(date1, finished, false);

        inputTaskDto1 = createDto(date1, pickedUp, true);
        inputTaskDto2 = createDto(date1, newTask, false);
        inputTaskDto3 = createDto(date1, finished, false);
    }

    @Test
    void getAllTask() {
        given(taskService.getAllTask()).willReturn(List.of(taskDto1, taskDto2, taskDto3));

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].status", is(pickedUp)))
                .andExpect(jsonPath("$[1].status", is(newTask)))
                .andExpect(jsonPath("$[2].status", is(finished)));
    }

    public TaskDto createDto(LocalDate date, String status, boolean assigned) {
        TaskDto taskDto = new TaskDto();
        taskDto.dueDate = date;
        taskDto.status = status;
        taskDto.assigned = assigned;
        return taskDto;
    }


}

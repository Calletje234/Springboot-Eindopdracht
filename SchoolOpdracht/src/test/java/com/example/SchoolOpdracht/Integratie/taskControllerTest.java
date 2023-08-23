package com.example.SchoolOpdracht.Integratie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.controller.TaskController;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.service.TaskService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;


@WebMvcTest(TaskController.class)
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
    void getAllTask() throws Exception {
        given(taskService.getAllTask()).willReturn(List.of(taskDto1, taskDto2, taskDto3));
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(pickedUp))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].assigned").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].assigned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value(newTask))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].status").value(finished))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].assigned").value(false));
    }

    public TaskDto createDto(LocalDate date, String status, boolean assigned) {
        TaskDto taskDto = new TaskDto();
        taskDto.dueDate = date;
        taskDto.status = status;
        taskDto.assigned = assigned;
        return taskDto;
    }


}

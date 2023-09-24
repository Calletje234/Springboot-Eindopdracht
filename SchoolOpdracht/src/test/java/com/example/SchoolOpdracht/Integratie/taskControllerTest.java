package com.example.SchoolOpdracht.Integratie;

import java.time.LocalDate;
import java.util.List;

import com.example.SchoolOpdracht.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class taskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository  taskRepo;

    private Long taskId1;
    private Long taskId2;
    private Long taskId3;
    private Long taskId4;

    private final LocalDate date1 = LocalDate.of(2023, 5, 10);
    private final LocalDate date2 = LocalDate.of(2022, 9,15);

    private final String status1 = "Picked up";
    private final String status2 = "New";
    private final String status3 = "In progress";
    private final String status4 = "Done";

    @BeforeEach
    public void setUp() {
        Task task1 = new Task(status1, date1, true);
        Task task2 = new Task(status3, date2, false);
        Task task3 = new Task(status4, date2, false);
        Task task4 = new Task(status2, date1, true);

        taskId1 = taskRepo.save(task1).getTaskId();
        taskId2 = taskRepo.save(task2).getTaskId();
        taskId3 = taskRepo.save(task3).getTaskId();
        taskId4 = taskRepo.save(task4).getTaskId();
    }

    @AfterEach
    public void tearDown() {
        if (taskRepo.findById(taskId1).isPresent()) {
            taskRepo.deleteById(taskId1);
        }
        if (taskRepo.findById(taskId2).isPresent()) {
            taskRepo.deleteById(taskId2);
        }
        if (taskRepo.findById(taskId3).isPresent()) {
            taskRepo.deleteById(taskId3);
        }
        if (taskRepo.findById(taskId4).isPresent()) {
            taskRepo.deleteById(taskId4);
        }
    }

    @Test
    void getAllTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));
    }

    @Test
    void getTaskById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/" + taskId1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(status1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dueDate").value(date1.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assigned").value(true));
    }

    @Test
    void checkIfTaskIsOverdue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/checkDueDate/" + taskId1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(false));
    }

    @Test
    void getAmountOfDays() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/daysBeforeOverDue/" + taskId1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(0));
    }

    @Test
    void createTask() throws Exception {
        TaskDto taskDto = createDto(date1, status1, true);
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .contentType("application/json")
                .content(asJsonString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void changeTeacher() throws Exception {
        TaskDto taskDto = createDto(date1, status1, true);
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/changeTeacher/" + taskId1)
                .contentType("application/json")
                .content(asJsonString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTaskStatus() throws Exception {
        TaskDto taskDto = createDto(date1, status1, true);
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/updateStatus/" + taskId1)
                .contentType("application/json")
                .content(asJsonString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateDueDate() throws Exception {
        TaskDto taskDto = createDto(date1, status1, true);
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/updateDueDate/" + taskId1)
                .contentType("application/json")
                .content(asJsonString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/" + taskId1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    public TaskDto createDto(LocalDate date, String status, boolean assigned) {
        TaskDto taskDto = new TaskDto();
        taskDto.dueDate = date;
        taskDto.status = status;
        taskDto.assigned = assigned;
        return taskDto;
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}

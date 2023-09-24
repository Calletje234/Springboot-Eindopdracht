package com.example.SchoolOpdracht.Integratie;

import com.example.SchoolOpdracht.controller.OpmerkingController;
import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OpmerkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OpmerkingenRepository opmerkingRepo;

    @Autowired
    private TaskRepository taskRepo;

    private Long opmerking1Id;
    private Long opmerking2Id;
    private Long taskId1;
    private Long taskId2;

    private final LocalDate date = LocalDate.of(2023, 5, 10);
    private final LocalDate date2 = LocalDate.of(2022, 9,15);

    private final String opmerkingComment1 = "Parent doesn't now when to pick up child";
    private final String opmerkingComment2 = "Parent want's to thank teacher for helping child";

    @BeforeEach
    void setUp() {
        Task task1 = createTask("Picked up", date, true);
        Task task2 = createTask("New", date2, false);
        taskId1 = taskRepo.save(task1).getTaskId();
        taskId2 = taskRepo.save(task2).getTaskId();

        Opmerkingen opmerking1 = createOpmerkingWithoutTask(date, opmerkingComment1);
        Opmerkingen opmerking2 = createOpmerkingWithTask(date, opmerkingComment2, task1);
        opmerking1Id = opmerkingRepo.save(opmerking1).getOpmerkingenId();
        opmerking2Id = opmerkingRepo.save(opmerking2).getOpmerkingenId();
    }

    @AfterEach
    void tearDown() {
        if (opmerkingRepo.existsById(opmerking1Id)) {
            opmerkingRepo.deleteById(opmerking1Id);
        }
        if (opmerkingRepo.existsById(opmerking2Id)) {
            opmerkingRepo.deleteById(opmerking2Id);
        }
        if (taskRepo.existsById(taskId1)) {
            taskRepo.deleteById(taskId1);
        }
        if (taskRepo.existsById(taskId2)) {
            taskRepo.deleteById(taskId2);
        }
    }

    @Test
    void getAllOpmerkingen() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/opmerking"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].dateOfContact").value(date.toString()))
                .andExpect(jsonPath("$[0].opmerking").value(opmerkingComment1))
                .andExpect(jsonPath("$[1].dateOfContact").value(date.toString()))
                .andExpect(jsonPath("$[1].opmerking").value(opmerkingComment2))
                .andExpect(status().isOk());
    }

    @Test
    void getOpmerkingById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/opmerking/" + opmerking1Id))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.dateOfContact").value(date.toString()))
                .andExpect(jsonPath("$.opmerking").value(opmerkingComment1))
                .andExpect(status().isOk());
    }

    @Test
    void createOpmerking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/opmerking")
                    .content(asJsonString(createDto(date, opmerkingComment1, taskId2)))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Opmerking created"))
                .andExpect(status().isCreated());
    }

    @Test
    void changeOpmerking() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/opmerking/changeOpmerking/" + opmerking1Id)
                    .content(asJsonString(createDto(date, opmerkingComment2, taskId1)))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dateOfContact").value(date.toString()))
                .andExpect(jsonPath("$.opmerking").value(opmerkingComment2))
                .andExpect(status().isOk());
    }

    @Test
    void changeContactDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/opmerking/changeContactDate/" + opmerking1Id)
                    .content(asJsonString(createDto(date2, opmerkingComment1, taskId1)))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dateOfContact").value(date2.toString()))
                .andExpect(jsonPath("$.opmerking").value(opmerkingComment1))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOpmerkingById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/opmerking/" + opmerking1Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateOfContact").value(date.toString()))
                .andExpect(jsonPath("$.opmerking").value(opmerkingComment1));
    }

    public OpmerkingenDto createDto(LocalDate date, String opmerking, Long taskId) {
        OpmerkingenDto opmerkingDto = new OpmerkingenDto();
        opmerkingDto.taskId = taskId;
        opmerkingDto.dateOfContact = date;
        opmerkingDto.opmerking = opmerking;
        return opmerkingDto;
    }

    public Opmerkingen createOpmerkingWithTask(LocalDate date, String opmerking, Task task) {
        Opmerkingen opmerkingen = new Opmerkingen();
        opmerkingen.setDateOfContact(date);
        opmerkingen.setOpmerking(opmerking);
        opmerkingen.setNewTask(task);
        return opmerkingen;
    }

    public Opmerkingen createOpmerkingWithoutTask(LocalDate date, String opmerking) {
        Opmerkingen opmerkingen = new Opmerkingen();
        opmerkingen.setDateOfContact(date);
        opmerkingen.setOpmerking(opmerking);
        return opmerkingen;
    }

    public Task createTask(String status, LocalDate dueDate, boolean assigned) {
        Task task = new Task();
        task.setStatus(status);
        task.setDueDate(dueDate);
        task.setAssigned(assigned);
        return task;
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
package com.example.SchoolOpdracht.Integratie;

import com.example.SchoolOpdracht.controller.OpmerkingController;

import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.service.OpmerkingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class OpmerkingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllOpmerkingen() throws Exception {
    }

    @Test
    void getOpmerkingById() {
    }

    @Test
    void createOpmerking() {
    }

    @Test
    void removeOpmerkingById() {
    }

    @Test
    void changeOpmerking() {
    }

    @Test
    void changeContactDate() {
    }

    @Test
    void deleteOpmerkingById() {
    }

    public OpmerkingenDto createDto(LocalDate date, String opmerking) {
        OpmerkingenDto opmerkingDto = new OpmerkingenDto();
        opmerkingDto.dateOfContact = date;
        opmerkingDto.opmerking = opmerking;
        return opmerkingDto;
    }
}
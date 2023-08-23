package com.example.SchoolOpdracht.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class OpmerkingServiceTest {
    public Opmerkingen  opmerkingen;

    @Mock
    OpmerkingenRepository opmerkrepos;

    @Mock
    TaskRepository taskRepos;

    @InjectMocks
    OpmerkingService opmerkService;

    @BeforeEach
    void testSetup() {
        opmerkingen = new Opmerkingen(LocalDate.of(2023, 05, 10), "test");
    }

    @Test
    void createOpmerking() {
    }

    @Test
    void getOpmerkingById() {
        // arrange
        Mockito.when(opmerkrepos.findById(anyLong())).thenReturn(Optional.of(opmerkingen));

        // act
        OpmerkingenDto opmerkingenDto = opmerkService.getOpmerkingById(1L);

        // assert
        assertEquals(opmerkingenDto.dateOfContact, LocalDate.of(2023, 05, 10));
        assertEquals(opmerkingenDto.opmerking, "test");
    }
}

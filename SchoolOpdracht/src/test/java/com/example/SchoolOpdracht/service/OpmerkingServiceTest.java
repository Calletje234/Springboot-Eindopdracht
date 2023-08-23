package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class OpmerkingServiceTest {

    Opmerkingen opmerking1;
    Opmerkingen opmerking2;
    Opmerkingen opmerking3;

    Task task1;
    Task task2;
    Task task3;

    OpmerkingenDto opmerkingDto1;

    LocalDate date1 = LocalDate.of(2023, 5, 10);

    @Mock
    OpmerkingenRepository repos;

    @Mock
    TaskRepository taskRepos;

    @InjectMocks
    OpmerkingService service;

    @BeforeEach
    void setUp() {
        task1 = new Task("Picked up", date1, true);
        task2 = new Task("New", date1, false);
        task3 = new Task("Finished", date1, false);

        opmerking1 = new Opmerkingen(1L, date1, "I bless the rains down in Africa", task1);
        opmerking2 = new Opmerkingen(2L, date1, "Just a small town girl, Livin' in a lonely world", task2);
        opmerking3 = new Opmerkingen(3L, date1, "You made me a, you made me a believer, believer", task3);

        opmerkingDto1 = new OpmerkingenDto();
        opmerkingDto1.opmerking = "I bless the rains down in Africa";
        opmerkingDto1.dateOfContact = date1;
        opmerkingDto1.taskId = 1L;
    }

    @Test
    void createOpmerking() {
        // arrange
        Mockito.when(repos.save(ArgumentMatchers.any(Opmerkingen.class))).thenReturn(opmerking1);

        // act
        Long result = service.createOpmerking(opmerkingDto1);

        // assert
        assertEquals(1L, result, "createOpmerking should return 1L");
    }

    @Test
    void getAllOpmerkingen() {
        // arrange
        Mockito.when(repos.findAll()).thenReturn(java.util.Arrays.asList(opmerking1, opmerking2, opmerking3));

        // act
        Iterable<OpmerkingenDto> allOpmerkingen = service.getAllOpmerkingen();

        // assert
        assertEquals(3, ((java.util.Collection<?>) allOpmerkingen).size(), "getAllOpmerkingen should return 3 opmerkingen");
    }

    @Test
    void getOpmerkingById() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(opmerking1));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        // act
        OpmerkingenDto result = service.getOpmerkingById(1L);

        // assert
        assertEquals(opmerking1.getOpmerking(), result.opmerking, "getOpmerkingById should return opmerking1");
        assertEquals(opmerking1.getDateOfContact(), result.dateOfContact, "getOpmerkingById should return opmerking1");
        assertEquals(opmerking1.getNewTask().getTaskId(), result.taskId, "getOpmerkingById should return opmerking1");
    }

    @Test
    void changeOpmerking() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(opmerking1));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        OpmerkingenDto changeOpmerkingDto = new OpmerkingenDto();
        changeOpmerkingDto.opmerking = "This girl is on fire";

        // act
        OpmerkingenDto result = service.changeOpmerking(1L, changeOpmerkingDto);

        // assert
        assertEquals("This girl is on fire", result.opmerking, "changeOpmerking should return opmerking1 with changed opmerking");
    }

    @Test
    void changeContactDate() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(opmerking1));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        OpmerkingenDto changeDateDto = new OpmerkingenDto();
        changeDateDto.dateOfContact = LocalDate.of(2023, 5, 11);

        // act
        OpmerkingenDto result = service.changeContactDate(1L, changeDateDto);

        // assert
        assertEquals(LocalDate.of(2023, 5, 11), result.dateOfContact, "changeContactDate should return opmerking1 with changed date");
    }

    @Test
    void deleteOpmerkingById() {
    }

    @Test
    void createReturnDto() {
    }

    @Test
    void getTaskRepos() {
    }

    @Test
    void getOpmerkingRepos() {
    }
}
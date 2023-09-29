package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.AfwezigRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TeacherRepository;

import com.example.SchoolOpdracht.SchoolOpdracht.service.AfwezigService;
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
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
class AfwezigServiceTest {
    Afwezig afwezig;
    Afwezig afwezig2;
    Afwezig afwezig3;

    AfwezigDto afwezigDto;

    Teacher teacher;
    Teacher teacher2;
    Teacher teacher3;

    @Mock
    AfwezigRepository repos;

    @Mock
    TeacherRepository teacherRepos;

    @InjectMocks
    AfwezigService service;

    @BeforeEach
    void testSetup() {
        teacher = new Teacher("Ezio", "Auditore", 2);
        teacher2 = new Teacher("Desmond", "Miles", 3);
        teacher3 = new Teacher("Padma", "Patil", 4);

        afwezig = new Afwezig(1L, "Vacation", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15), teacher);
        afwezig2 = new Afwezig(2L, "Sick", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15), teacher2);
        afwezig3 = new Afwezig(3L, "Doctors Appointment", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15), teacher3);

        afwezigDto = new AfwezigDto();
        afwezigDto.endDate = LocalDate.of(2023, 10, 15);
        afwezigDto.teacherId = 1L;
        afwezigDto.reason = "Vacation";
        afwezigDto.startDate = LocalDate.of(2023, 10, 5);
    }

    @Test
    void createAfwezigPeriod() {
        // arrange
        Mockito.when(repos.save(ArgumentMatchers.any(Afwezig.class))).thenReturn(afwezig);
        Mockito.when(teacherRepos.findById(anyLong())).thenReturn(Optional.of(teacher));
        Mockito.when(teacherRepos.existsById(anyLong())).thenReturn(true);

        // act
        Long id = service.createAfwezigPeriod(afwezigDto);

        // assert
        assertEquals(1L, id, "createAfwezigPeriod should return 1L");
    }

    @Test
    void getAllAfwezig() {
        // arrange
        Mockito.when(repos.findAll()).thenReturn(java.util.Arrays.asList(afwezig, afwezig2, afwezig3));

        // act
        Iterable<AfwezigDto> allAfwezig = service.getAllAfwezig();

        // assert
        assertEquals(3, ((java.util.Collection<?>) allAfwezig).size(), "getAllAfwezig should return 3 afwezig periods");
    }

    @Test
    void shouldReturnCorrectAfwezig() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        // act
        AfwezigDto afto = service.getAfwezigById(1L);     

        //assert
        assertEquals("Vacation", afto.reason, "Reason doesn't match");
        assertEquals(LocalDate.of(2023, 10, 5), afto.startDate, "Start date doesn't match");
        assertEquals(LocalDate.of(2023, 10, 15), afto.endDate, "End date doesn't match");
    }

    @Test
    void changeReasonAfwezig() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        AfwezigDto changeReasonDto = new AfwezigDto();
        changeReasonDto.reason = "Sick";

        // act
        AfwezigDto afto = service.changeReasonAfwezig(1L, changeReasonDto);

        // assert
        assertEquals("Sick", afto.reason, "Reason doesn't match");
        assertEquals(LocalDate.of(2023, 10, 5), afto.startDate, "Start date doesn't match");
        assertEquals(LocalDate.of(2023, 10, 15), afto.endDate, "End date doesn't match");
    }

    @Test
    void changeStartingDate() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        AfwezigDto changeStartingDateDto = new AfwezigDto();
        changeStartingDateDto.startDate = LocalDate.of(2023, 10, 6);

        // act
        AfwezigDto afto = service.changeStartingDate(1L, changeStartingDateDto);

        // assert
        assertEquals("Vacation", afto.reason, "Reason doesn't match");
        assertEquals(LocalDate.of(2023, 10, 6), afto.startDate, "Start date doesn't match");
        assertEquals(LocalDate.of(2023, 10, 15), afto.endDate, "End date doesn't match");
    }

    @Test
    void changeEndingDate() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        AfwezigDto changeEndingDateDto = new AfwezigDto();
        changeEndingDateDto.endDate = LocalDate.of(2023, 10, 16);

        // act
        AfwezigDto afto = service.changeEndingDate(1L, changeEndingDateDto);

        // assert
        assertEquals("Vacation", afto.reason, "Reason doesn't match");
        assertEquals(LocalDate.of(2023, 10, 5), afto.startDate, "Start date doesn't match");
        assertEquals(LocalDate.of(2023, 10, 16), afto.endDate, "End date doesn't match");
    }

    @Test
    void deleteAfwezigById() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        // act
        AfwezigDto afto = service.deleteAfwezigById(1L);

        // assert
        assertEquals(afwezig.getReason(), afto.reason, "Reason doesn't match");
        assertEquals(afwezig.getStartDate(), afto.startDate, "Start date doesn't match");
        assertEquals(afwezig.getEndDate(), afto.endDate, "End date doesn't match");
    }

    @Test
    void createReturnDto() {
        // act
        AfwezigDto afto = service.createReturnDto(afwezig);

        // assert
        assertEquals(afwezig.getReason(), afto.reason, "Reason doesn't match");
        assertEquals(afwezig.getStartDate(), afto.startDate, "Start date doesn't match");
        assertEquals(afwezig.getEndDate(), afto.endDate, "End date doesn't match");
    }

    @Test
    void getAfwezigRepos() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));
        Mockito.when(repos.existsById(anyLong())).thenReturn(true);

        // act
        Afwezig result = service.getAfwezigRepos(1L);

        // assert
        assertEquals(afwezig.getReason(), result.getReason(), "getAfwezigRepos should return repos");
    }
}
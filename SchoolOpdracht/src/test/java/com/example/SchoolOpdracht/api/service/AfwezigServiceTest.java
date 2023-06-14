package com.example.SchoolOpdracht.api.service;

import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import com.example.SchoolOpdracht.service.AfwezigService;
import net.bytebuddy.asm.Advice;
import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AfwezigServiceTest {

    @Mock
    AfwezigRepository afwezigRepository;

    @Mock
    TeacherRepository teacherRepository;

    @Mock

    @InjectMocks
    AfwezigService service;

    Teacher teacher1;
    Afwezig afwezig1;

    @BeforeEach
    void setUp() {
        teacher1 = new Teacher(23L, "Calvin", "Boxtart", 3);
        afwezig1 = new Afwezig(1L, "Vacation", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15), teacher1);

    }

    @Test
    void shouldReturnCorrectAfwezig() {
        // arrange
        when(afwezigRepository.findById(1L)).thenReturn(Optional.of(afwezig1));
        when(afwezigRepository.existsById(1L)).thenReturn(true);
        when(teacherRepository.findById(23L)).thenReturn(Optional.of(teacher1));
        when(teacherRepository.existsById(23L)).thenReturn(true);

        // act
        AfwezigDto afto = service.getAfwezigById(1L);

        //assert
        verify(afwezigRepository, times(1)).findById(1L);
        verify(afwezigRepository, times(1)).existsById(1L);
        verify(teacherRepository, times(1)).findById(23L);
        verify(teacherRepository, times(1)).existsById(23L);
        assertEquals(afto.afwezigId(), afwezig1.getAfwezigId());
        assertEquals(afto.reason(), afwezig1.getReason());
        assertEquals(afto.startDate(), afwezig1.getStartDate());
        assertEquals(afto.endDate(), afwezig1.getEndDate());
        assertEquals(afto.teacherId(), afwezig1.getAfwezigTeacher().getTeacherId());
    }

    @Test
    void changeReasonAfwezig() {
    }

    @Test
    void changeStartingDate() {
    }

    @Test
    void changeEndingDate() {
    }

    @Test
    void changeTeacherId() {
    }

    @Test
    void deleteAfwezigById() {
    }

    @Test
    void createReturnDto() {
    }

    @Test
    void getAfwezigRepos() {
    }

}
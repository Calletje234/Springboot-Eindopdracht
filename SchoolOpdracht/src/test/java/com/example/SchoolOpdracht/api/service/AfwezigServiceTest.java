package com.example.SchoolOpdracht.api.service;

import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import com.example.SchoolOpdracht.service.AfwezigService;
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

    @BeforeEach
    void setUp() {
        teacher1 = new Teacher(23L, "Calvin", "Boxtart", 3);

    }

    @Test
    void shouldReturnCorrectAfwezig() {
        // arrange
        Afwezig afwezig = new Afwezig(1L,
                "Vacation",
                LocalDate.of(2023, 10, 5),
                LocalDate.of(2023, 10, 15),
                teacher1);

        AfwezigDto afwezigDto = new AfwezigDto();
        afwezigDto.reason = afwezig.getReason();
        afwezigDto.startDate = afwezig.getStartDate();
        afwezigDto.endDate = afwezig.getEndDate();

        when(repos.findById(1L)).thenReturn(Optional.of(afwezig));
        when(repos.existsById(1L)).thenReturn(true);

        // act
        AfwezigDto afto = service.getAfwezigById(1L);

        //assert
        verify(repos, times(1)).findById(1L);
        verify(repos, times(1)).existsById(1L);
        assertEquals(afto.reason, afwezigDto.reason);
        assertEquals(afto.startDate, afwezigDto.startDate);
        assertEquals(afto.endDate, afwezigDto.endDate);
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
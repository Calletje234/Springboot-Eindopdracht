package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.repository.AfwezigRepository;

import org.apache.tomcat.jni.Local;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    public Afwezig afwezig;

    @Mock
    AfwezigRepository repos;

    @Mock
    Util util;

    @InjectMocks
    AfwezigService service;

    @BeforeEach
    void testSetup() {
        Afwezig afwezig = new Afwezig("Vacation", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15));
        this.afwezig = afwezig;
    }

    @Test
    void createAfwezigPeriod() {
    }

    @Test
    void getAllAfwezig() {
    }

    @Test
    void shouldReturnCorrectAfwezig() {
        // arrange
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));

        // act
        AfwezigDto afto = service.getAfwezigById(1L);

        //assert
        assertEquals("Vacation", afto.reason, "Reason doesn't match");
        assertEquals(LocalDate.of(2023, 10, 5), afto.startDate, "Start date doesn't match");
        assertEquals(LocalDate.of(2023, 10, 15), afto.endDate, "End date doesn't match");
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
package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import org.checkerframework.checker.units.qual.A;
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

    @Mock
    AfwezigRepository repos;

    @InjectMocks
    AfwezigService service;

    @Test
    void createAfwezigPeriod() {
    }

    @Test
    void getAllAfwezig() {
    }

    @Test
    void shouldReturnCorrectAfwezig() {
        // arrange
        Afwezig afwezig = new Afwezig("Vacation", LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 15));
        Mockito.when(repos.findById(anyLong())).thenReturn(Optional.of(afwezig));

        AfwezigDto afto = service.getAfwezigById(1L);


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
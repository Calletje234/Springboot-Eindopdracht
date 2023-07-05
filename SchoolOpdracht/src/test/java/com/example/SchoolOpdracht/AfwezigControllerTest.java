package com.example.SchoolOpdracht;


import com.example.SchoolOpdracht.controller.AfwezigController;
import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.security.JwtService;
import com.example.SchoolOpdracht.service.AfwezigService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@WebMvcTest(AfwezigController.class)
class AfwezigControllerTest {
    public AfwezigDto afto;
    public AfwezigDto reasonto;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    AfwezigService afwezigService;

    @BeforeEach
    void testSetup() {
        this.afto.startDate = LocalDate.of(2023, 10, 5);
        this.afto.endDate = LocalDate.of(2023, 10, 15);
        this.afto.reason = "Vacation";
        this.afto.teacherId = 123456L;

        this.reasonto.startDate = LocalDate.of(2023, 10, 5);
        this.reasonto.endDate = LocalDate.of(2023, 10, 15);
        this.reasonto.reason = "Sick";
        this.reasonto.teacherId = 123456L;
    }

    @Test
    @WithMockUser(username="testuser", roles="USER")
    void retrieveAfwezigById() throws Exception {

        Mockito.when(afwezigService.getAfwezigById(123L)).thenReturn(this.afto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/afwezig/123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.startDate", is(LocalDate.of(2023, 10, 5))))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.endDate", is(LocalDate.of(2023, 10, 15))))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.reason", is("Vacation")))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.teacherId", is(123456L)));
    }

    @Test
    @WithMockUser(username="testuser", roles="USER")
    void changeReason() throws Exception {
        Mockito.when(afwezigService.changeReasonAfwezig(123L, reasonto)).thenReturn(this.reasonto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/afwezig/123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.startDate", is(LocalDate.of(2023, 10, 5))))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.endDate", is(LocalDate.of(2023, 10, 15))))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.reason", is(reasonto.reason)))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.teacherId", is(123456L)));
    }
}

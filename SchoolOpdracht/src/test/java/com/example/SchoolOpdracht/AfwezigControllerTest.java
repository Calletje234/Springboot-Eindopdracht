package com.example.SchoolOpdracht;


import com.example.SchoolOpdracht.controller.AfwezigController;
import com.example.SchoolOpdracht.service.AfwezigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AfwezigController.class)
class AfwezigControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    AfwezigService afwezigService;

    @Test
    @WithMockUser(username="testuser", roles="USER")
    void retrieveAfwezigById() throws Exception {

    }
}

package com.manpower.backendProject.services;

import com.manpower.backendProject.mocks.CustomMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationServiceTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomMocks mocks;

    @BeforeEach
    public void init() {
        mocks.getPersistedMockUser();
    }

    @Test
    public void givenCorrectCredentials_WhenLogin_ShouldSetAuthenticationContext_Status200() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"user.mock@email.com\", \"password\": \"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenUserNotExist_WhenLogin_Should_Status403() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"useraa.mock@email.com\", \"password\": \"1234\"}"))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void givenWrongPassword_WhenLogin_Should_Status403() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"user.mock@email.com\", \"password\": \"12345555\"}"))
                .andExpect(status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }
}
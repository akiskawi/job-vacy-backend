package com.manpower.backendProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestControllerTests {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET: not secured endpoint")
    public void givenNotAuthenticated_WhenGetTest_ShouldReturnDefaultMessage_Status200() throws Exception {
        this.mockMvc.perform(get("/test")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rest API working")))
                .andExpect(content().string(not(containsString("qq"))));
    }

    @Test
    @DisplayName("GET: secured endpoint, not logged in")
    public void givenNotAuthenticated_WhenGetSecuredEndpoint_Should_Status403() throws Exception {
        this.mockMvc.perform(get("/api/v1/secure"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    @DisplayName("GET: secured endpoint, logged in")
    public void givenAuthenticated_WhenGetSecuredEndpoint_ShouldReturnDefaultMessage_Status200() throws Exception {
        this.mockMvc.perform(get("/api/v1/secure"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Secure endpoint")))
                .andDo(print());
    }
}
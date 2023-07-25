package com.manpower.backendProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(NotSecureController.class)
public class NotSecureControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void WhenGetTest_ShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/test")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rest API working")))
                .andExpect(content().string(not(containsString("qq"))));
    }
}

package com.manpower.backendProject.services;

import com.manpower.backendProject.mocks.CustomMocks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminServiceTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomMocks mocks;

    @Test
    @WithMockUser
    public void whenRequestUsers_shouldReturnListOfUsers() {
        //TODO:
    }
}

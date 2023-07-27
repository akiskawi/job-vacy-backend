package com.manpower.backendProject.controllers;

import com.manpower.backendProject.mocks.CustomMocks;
import com.manpower.backendProject.models.auth.RegisterRequest;
import com.manpower.backendProject.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.manpower.backendProject.mocks.CustomMocks.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminUserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService service;

    @Autowired
    CustomMocks mocks;

    // GET USERS TESTS
    @Test
    @WithMockUser
    public void givenRoleUser_WhenGetUsers_Should_Status403() throws Exception {
        this.mockMvc.perform(get("/api/v1/admin/users"))
                .andExpect(content().string(""))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"MANAGER"})
    public void givenRoleManager_WhenGetUsers_Should_Status403() throws Exception {
        this.mockMvc.perform(get("/api/v1/admin/users"))
                .andExpect(content().string(""))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenRoleAdmin_WhenGetUsers_ShouldReturnUsers_Status200() throws Exception {
        given(this.service.getUsers(0, 0, "id"))
                .willReturn(
                        ResponseEntity.ok().body(List.of(CustomMocks.mockUser()))
                );

        this.mockMvc.perform(get("/api/v1/admin/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"lastname\":\"Mock\"")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // CREATE USER TESTS
    @Test
    @WithMockUser(roles = {"USER", "MANAGER"})
    public void givenRoleUserOrManager_WhenCreateUser_Should_Status403() throws Exception {
        this.mockMvc.perform(post("/api/v1/admin/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenBlankEmail_WhenAdminCreateUser_Should_Status400() throws Exception {
        RegisterRequest mockRequest = CustomMocks.mockRegisterRequest();
        mockRequest.setEmail("");
        this.mockMvc.perform(post("/api/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenInvalidEmailFormat_WhenAdminCreateUser_Should_Status400() throws Exception {
        RegisterRequest mockRequest = CustomMocks.mockRegisterRequest();
        mockRequest.setEmail("aaa");
        this.mockMvc.perform(post("/api/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenBlankPassword_WhenAdminCreateUser_Should_Status400() throws Exception {
        RegisterRequest mockRequest = CustomMocks.mockRegisterRequest();
        mockRequest.setPassword("");
        this.mockMvc.perform(post("/api/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenValidForm_WhenAdminCreateUser_Should_Status201() throws Exception {
        this.mockMvc.perform(post("/api/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRegisterRequest())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    // UPDATE USER TESTS
    @Test
    @WithMockUser(roles = {"USER", "MANAGER"})
    public void givenRoleUserOrManager_WhenUpdateUser_Should_Status403() throws Exception {
        this.mockMvc.perform(put("/api/v1/admin/users/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DirtiesContext
    public void givenValidForm_WhenAdminUpdateUser_Should_Status200() throws Exception {
        mocks.getPersistedMockUser();
        this.mockMvc.perform(put("/api/v1/admin/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUpdateUser())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER", "MANAGER"})
    @DirtiesContext
    public void whenUserOrManagerDeactivateUser_Should_Status200() throws Exception {
        this.mockMvc.perform(delete("/api/v1/admin/users/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DirtiesContext
    public void whenAdminDeactivateUser_Should_Status200() throws Exception {
        this.mockMvc.perform(delete("/api/v1/admin/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER", "MANAGER"})
    @DirtiesContext
    public void whenUserOrManagerActivateUser_Should_Status200() throws Exception {
        this.mockMvc.perform(delete("/api/v1/admin/users/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DirtiesContext
    public void whenAdminActivateUser_Should_Status200() throws Exception {
        this.mockMvc.perform(post("/api/v1/admin/users/1"))
                .andExpect(status().isOk());
    }
}
package com.manpower.backendProject;

import com.manpower.backendProject.config.*;
import com.manpower.backendProject.controllers.*;
import com.manpower.backendProject.exception.*;
import com.manpower.backendProject.services.*;
import com.manpower.backendProject.test.*;
import com.manpower.backendProject.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTests {
    @Test
    public void configsLoad(@Autowired ApplicationConfig applicationConfig,
                            @Autowired JwtAuthenticationFilter jwtAuthenticationFilter,
                            @Autowired OpenAPIConfig openAPIConfig,
                            @Autowired SecurityConfig securityConfig) {
        assertThat(applicationConfig).isNotNull();
        assertThat(jwtAuthenticationFilter).isNotNull();
        assertThat(openAPIConfig).isNotNull();
        assertThat(securityConfig).isNotNull();
    }

    @Test
    public void controllersLoad(@Autowired AdminController adminController,
                                @Autowired AuthenticationController authenticationController,
                                @Autowired ManagerController managerController,
                                @Autowired UserController userController,
                                @Autowired TestController testController) {
        assertThat(adminController).isNotNull();
        assertThat(authenticationController).isNotNull();
        assertThat(managerController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(testController).isNotNull();
    }

    @Test
    public void exceptionsLoad(@Autowired GlobalExceptionHandler globalExceptionHandler,
                               @Autowired ValidationExceptionHandler validationExceptionHandler) {
        assertThat(globalExceptionHandler).isNotNull();
        assertThat(validationExceptionHandler).isNotNull();
    }

    @Test
    public void servicesLoad(@Autowired AdminService adminService,
                             @Autowired AuthenticationService authenticationService,
                             @Autowired JwtService jwtService,
                             @Autowired LogoutService logoutService,
                             @Autowired ManagerService managerService,
                             @Autowired UserService userService) {
        assertThat(adminService).isNotNull();
        assertThat(authenticationService).isNotNull();
        assertThat(jwtService).isNotNull();
        assertThat(logoutService).isNotNull();
        assertThat(managerService).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    public void utilsLoad(
//        @Autowired EmailSender emailSender,
            @Autowired Initializer initializer) {
//        assertThat(emailSender).isNotNull();
        assertThat(initializer).isNotNull();
    }
}

package com.manpower.backendProject;

import com.manpower.backendProject.models.dao.RegisterRequest;
import com.manpower.backendProject.services.AdminService;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootApplication
public class BackendProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);
    }

}

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

    @Bean
    public CommandLineRunner commandLineRunner(
            AdminService service,
            PasswordEncoder passwordEncoder,
            UserRepository repository,
            TeamRepository teamRepository
    ) {
        return args -> {
            var team1 = Team
                    .builder()
                    .build();
            teamRepository.save(team1);

            var admin1 = User.builder()
                    .firstname("Admin1")
                    .lastname("Last")
                    .email("admin1@email.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(List.of(Role.ADMIN, Role.MANAGER, Role.USER))
                    .enabled(true)
                    .team(team1)
                    .teamManager(team1)
                    .build();
            repository.save(admin1);

            var manager1 = User.builder()
                    .firstname("Manager1")
                    .lastname("Last")
                    .email("manager1@email.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(List.of(Role.USER))
                    .enabled(true)
                    .team(team1)
                    .build();
            repository.save(manager1);

            var user1 = User.builder()
                    .firstname("User1")
                    .lastname("Last")
                    .email("user1@email.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(List.of(Role.USER))
                    .enabled(true)
                    .team(team1)
                    .build();
            repository.save(user1);

//            User adminWithId = repository.findByEmail("admin1@email.com").get();
//            User managerWithId = repository.findByEmail("manager1@email.com").get();
//            User userWithId = repository.findByEmail("user1@email.com").get();


            var admin = RegisterRequest
                    .builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@email.com")
                    .password("1234")
                    .roles(List.of(Role.ADMIN, Role.MANAGER, Role.USER))
                    .build();
            var manager = RegisterRequest
                    .builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@email.com")
                    .password("1234")
                    .roles(List.of(Role.MANAGER, Role.USER))
                    .build();
            var user = RegisterRequest
                    .builder()
                    .firstname("user")
                    .lastname("user")
                    .email("user@email.com")
                    .password("1234")
                    .roles(List.of(Role.USER))
                    .build();
            var adminUser = RegisterRequest
                    .builder()
                    .firstname("Admin User")
                    .lastname("Admin User")
                    .email("auser@email.com")
                    .password("1234")
                    .roles(List.of(Role.ADMIN, Role.USER))
                    .build();

            service.createUser(admin);
            service.createUser(manager);
            service.createUser(user);
            service.createUser(adminUser);


        };
    }

}

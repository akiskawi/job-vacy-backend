package com.manpower.backendProject;

import com.manpower.backendProject.auth.AuthenticationResponse;
import com.manpower.backendProject.auth.AuthenticationService;
import com.manpower.backendProject.auth.RegisterRequest;
import com.manpower.backendProject.team.Team;
import com.manpower.backendProject.team.TeamRepository;
import com.manpower.backendProject.user.Role;
import com.manpower.backendProject.user.User;
import com.manpower.backendProject.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class BackendProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
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

            var tokens = new HashMap<String, AuthenticationResponse>();
            tokens.put("admin", service.register(admin));
            tokens.put("manager", service.register(manager));
            tokens.put("user", service.register(user));
            tokens.put("adminUser", service.register(adminUser));

            tokens.forEach((x, y) -> System.out.println(x + " = " + y));

        };
    }

}

package com.manpower.backendProject.util;

import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findAll().stream().findFirst().isPresent()) {
            return;
        }

        var teamInit = Team
                .builder()
                .build();

        Team team = teamRepository.save(teamInit);

        var admin = User.builder()
                .firstname("Super")
                .lastname("Admin")
                .email("admin@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.ADMIN))
                .enabled(true)
                .build();

        var manager = User.builder()
                .firstname("Mana")
                .lastname("Manager")
                .email("manager@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.MANAGER))
                .team(team)
                .enabled(true)
                .build();

        var stavros = User.builder()
                .firstname("Stavros")
                .lastname("Spil")
                .email("stavros@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.USER))
                .team(team)
                .enabled(true)
                .build();

        var akis = User.builder()
                .firstname("Akis")
                .lastname("Pro")
                .email("akis@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.USER))
                .team(team)
                .enabled(true)
                .build();

        User adminDB = userRepository.save(admin);
        User managerDB = userRepository.save(manager);
        User stavrosDB = userRepository.save(stavros);
        User akisDB = userRepository.save(akis);

        team.setManager(managerDB);
//        team.setMembers(List.of(stavrosDB, akisDB));
        teamRepository.save(team);
    }
}
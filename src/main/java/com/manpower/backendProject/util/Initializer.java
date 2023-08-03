package com.manpower.backendProject.util;

import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.leave.LeaveRequestSTATUS;
import com.manpower.backendProject.models.leave.LeaveRequestTYPE;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDays;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.LeaveRequestAvailableDaysRepository;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final LeaveRequestRepository requestRepository;
    private final LeaveRequestAvailableDaysRepository availableDaysRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findAll().stream().findFirst().isPresent()) {
            return;
        }

        var teamInit = Team
                .builder()
                .build();

        Team team = teamRepository.save(teamInit);

        for (int i=0; i<10; i++) {
            var user = User.builder()
                    .firstname("User"+i)
                    .lastname("Mock")
                    .email("user"+i+"@email.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(List.of(Role.USER))
                    .enabled(true)
                    .build();
            userRepository.save(user);
        }

        var admin = User.builder()
                .firstname("Admin")
                .lastname("Mock")
                .email("admin@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.ADMIN))
                .enabled(true)
                .build();

        var manager = User.builder()
                .firstname("Manager")
                .lastname("Test")
                .email("manager@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.MANAGER))
//                .team(team)
                .enabled(true)
                .build();

        var amanager = User.builder()
                .firstname("Admin-Manager")
                .lastname("Mock")
                .email("amanager@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.ADMIN, Role.MANAGER))
                .team(team)
                .enabled(true)
                .build();


        var auser = User.builder()
                .firstname("Admin-User")
                .lastname("Test")
                .email("auser@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.ADMIN, Role.USER))
                .team(team)
                .enabled(true)
                .build();

        User adminDB = userRepository.save(admin);
        User managerDB = userRepository.save(manager);
        User stavrosDB = userRepository.save(amanager);
        User akisDB = userRepository.save(auser);

        var leave = LeaveRequest
                .builder()
                .startDate(LocalDate.of(2023, 1, 10))
                .endDate(LocalDate.of(2023, 2, 10))
                .type(LeaveRequestTYPE.KANONIKI)
                .status(LeaveRequestSTATUS.PENDING)
                .requestsUser(akisDB)
                .build();
        requestRepository.save(leave);

        var availableDays = LeaveRequestAvailableDays
                .builder()
                .type(LeaveRequestTYPE.KANONIKI)
                .remaining((short) 20)
                .taken((short) 10)
                .usersLeaveRequestsRemainingDays(akisDB)
                .build();
        availableDaysRepository.save(availableDays);
        team.setManager(managerDB);
        team.setMembers(List.of(stavrosDB, akisDB));
        teamRepository.save(team);
    }
}
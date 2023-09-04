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

        var team = Team.builder()
                .build();
        Team teamDB = teamRepository.save(team);

        var manager = User.builder()
                .firstname("Panos")
                .lastname("Lastname")
                .email("manager@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.MANAGER))
                .managedTeam(teamDB)
                .enabled(true)
                .build();
        User managerDB = userRepository.save(manager);


        var user1 = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("user1@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.USER))
                .team(teamDB)
                .enabled(true)
                .build();
        User user1DB = userRepository.save(user1);

        var user2 = User.builder()
                .firstname("Jack")
                .lastname("Bean")
                .email("user2@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.USER))
                .team(teamDB)
                .enabled(true)
                .build();
        User user2DB = userRepository.save(user2);

        var admin = User.builder()
                .firstname("Sevi")
                .lastname("Lastname")
                .email("admin@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.ADMIN))
                .enabled(true)
                .build();
        User adminDB = userRepository.save(admin);

        teamDB.setMembers(List.of(user1DB, user2DB));
        team.setManager(managerDB);
        teamRepository.save(teamDB);

        var leave = LeaveRequest
                .builder()
                .startDate(LocalDate.of(2024, 1, 10))
                .endDate(LocalDate.of(2024, 1, 13))
                .type(LeaveRequestTYPE.KANONIKI)
                .status(LeaveRequestSTATUS.PENDING)
                .user(user1DB)
                .build();
        requestRepository.save(leave);

        var availableDays = LeaveRequestAvailableDays
                .builder()
                .type(LeaveRequestTYPE.KANONIKI)
                .remaining((short) 19)
                .taken((short) 10)
                .user(user1DB)
                .build();
        availableDaysRepository.save(availableDays);
    }
}
package com.manpower.backendProject.util;

import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.leave.LeaveRequestSTATUS;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDays;
import com.manpower.backendProject.models.leave_types.LeaveType;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final LeaveRequestRepository requestRepository;
    private final LeaveRequestAvailableDaysRepository availableDaysRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    @Override
    public void run(String... args) throws Exception {

        Map<String, LeaveType> leaveTypes = initialLeaveTypes();

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
                .type(leaveTypes.get("kanoniki"))
                .status(LeaveRequestSTATUS.PENDING)
                .user(user1DB)
                .build();
        requestRepository.save(leave);

        var availableDays = LeaveRequestAvailableDays
                .builder()
                .type(leaveTypes.get("kanoniki"))
                .remaining((short) 19)
                .taken((short) 10)
                .user(user1DB)
                .build();
        availableDaysRepository.save(availableDays);
    }

    private Map<String, LeaveType> initialLeaveTypes() {
        Map<String, LeaveType> leaveTypes = new HashMap<>();

        LeaveType kanoniki = LeaveType.builder()
                .name("Κανονική")
                .description("Η άδεια αυτή έχει όριο ημερών περίπου 20-25 μέρες το χρόνο ανάλογα την προϋπηρεσία.")
                .hasDayLimit(true)
                .build();

        LeaveType aa = LeaveType.builder()
                .name("Άνευ Αποδοχών")
                .description("Η άδεια αυτή δεν πληρώνεται.")
                .hasDayLimit(true)
                .build();

        LeaveType astheneias = LeaveType.builder()
                .name("Ασθενείας")
                .description("Η άδεια αυτή απαιτεί χαρτί γιατρού.")
                .hasDayLimit(true)
                .requiresDocuments(true)
                .build();

        LeaveType thlergasias = LeaveType.builder()
                .name("Τηλεργασίας")
                .description("Η άδεια αυτή χρησιμοποιείται για τηλεργασία.")
                .hasDayLimit(true)
                .build();

        leaveTypes.put("kanoniki", leaveTypeRepository.save(kanoniki));
        leaveTypes.put("aa", leaveTypeRepository.save(aa));
        leaveTypes.put("astheneias", leaveTypeRepository.save(astheneias));
        leaveTypes.put("thlergasias", leaveTypeRepository.save(thlergasias));
        return leaveTypes;
    }
}
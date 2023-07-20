package com.manpower.backendProject.services;

import com.manpower.backendProject.models.team.CreateTeamDao;
import com.manpower.backendProject.models.team.TeamDao;
import com.manpower.backendProject.models.team.TeamNotFoundException;
import com.manpower.backendProject.models.user.*;
import com.manpower.backendProject.models.auth.*;
import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.EntityToDaoHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository repository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final LeaveRequestRepository leaveRequestRepository;


    public ResponseEntity<Object> getUsers(int pageNo, int pageSize, String sortBy) {
        if (pageSize > 0) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<User> users = repository.findAll(paging);
            return ResponseEntity.ok(users.stream().map(EntityToDaoHelper::userToUserDao).toList());
        } else {
            var users = repository.findAll();
            return ResponseEntity.ok(users.stream().map(EntityToDaoHelper::userToUserDao).toList());
        }
    }

    public ResponseEntity<String> createUser(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .enabled(true)
                .build();
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists.");
        }
        repository.save(user);
        return ResponseEntity.ok("User created successfully.");
    }

    public ResponseEntity<String> updateUser(int id, UpdateUser request) {
        var user = getSingleUser(id);
        if (request.getFirstname() != null) user.setFirstname(request.getFirstname());
        if (request.getLastname() != null) user.setLastname(request.getLastname());
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getRoles() != null) user.setRoles(request.getRoles());
        repository.save(user);
        return ResponseEntity.ok("User updated successfully.");
    }

    public ResponseEntity<String> deActiveAccount(int id) {
        var user = getSingleUser(id);
        user.setEnabled(false);
        repository.save(user);
        return ResponseEntity.ok("User deactivated successfully.");
    }

    public ResponseEntity<String> activeAccount(int id) {
        var user = getSingleUser(id);
        user.setEnabled(true);
        repository.save(user);
        return ResponseEntity.ok("User activated successfully.");
    }

    public User getSingleUser(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public ResponseEntity<String> createTeamWithManagerAndMembers(CreateTeamDao createTeamDao) {
        User manager = repository.findById(createTeamDao.getManagerId()).orElseThrow(() -> new UserNotFoundException("Manager not found."));
        List<User> members = createTeamDao.getMembersId().stream().map(id -> repository.findById(id).orElseThrow(() -> new UserNotFoundException("Manager not found."))).toList();
        Team team = Team
                .builder()
                .manager(manager)
                .members(members)
                .build();
        teamRepository.save(team);
        return ResponseEntity.ok("Team created!");
    }

    public ResponseEntity<String> updateTeamWithManagerAndMembers(int id, CreateTeamDao createTeamDao) {
        Team team = findTeamById(id);
        User manager = repository.findById(createTeamDao.getManagerId()).orElseThrow(() -> new UserNotFoundException("Manager not found."));
        List<User> members = createTeamDao.getMembersId().stream().map(userId -> repository.findById(userId).orElseThrow(() -> new UserNotFoundException("Manager not found."))).toList();
        team.setManager(manager);
        team.setMembers(members);
        teamRepository.save(team);
        return ResponseEntity.ok("Updated Team successfully.");
    }

    public ResponseEntity<String> deleteTeamById(int id) {
        Team team = findTeamById(id);
        teamRepository.delete(team);
        return ResponseEntity.ok("Deleted Team successfully.");
    }

    private Team findTeamById(int teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found!")); //throw something
    }

    public ResponseEntity<Object> getTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDao> teamDaos = teams.stream().map(team ->
                TeamDao.builder()
                        .id(team.getId())
                        .manager(UserDao.buildUserDao(team.getManager()))
                        .members(
                                team.getMembers().stream().map(
                                        UserDao::buildUserDao
                                ).toList()
                        )
                        .build())
                .toList();
        return ResponseEntity.ok(teamDaos);
    }

    public ResponseEntity<Object> getRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        return ResponseEntity.ok(leaveRequests);
    }
 }

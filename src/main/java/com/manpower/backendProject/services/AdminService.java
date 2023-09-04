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
import com.manpower.backendProject.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final LeaveRequestRepository leaveRequestRepository;
    private final PaginationUtil<User, UserDao> userPaginationUtil;

    public ResponseEntity<Object> getUsers(int pageNo, int pageSize, String sortBy) {
        Pageable paging = userPaginationUtil.getPageable(pageNo, pageSize, sortBy);
        Page<User> page = userRepository.findAll(paging);
        return userPaginationUtil.getPaginatedResponse(UserDao::userDaoConverter, page);
//
//        if (pageNo < 0) pageNo = 0;
//        if (pageSize < 1) pageSize = 10;
//
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<User> page = repository.findAll(paging);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("content", page.getContent().stream().map(UserDao::userDaoConverter).toList());
//        response.put("currentPage", page.getNumber() + 1);
//        response.put("pages", page.getTotalPages());
//        response.put("count", page.getTotalElements());
//
//        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> createUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .enabled(true)
                .build();
        User createdUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Void> updateUser(long id, UpdateUser request) {
        var user = getSingleUser(id);
        if (request.getFirstname() != null) user.setFirstname(request.getFirstname());
        if (request.getLastname() != null) user.setLastname(request.getLastname());
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getRoles() != null) user.setRoles(request.getRoles());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    public ResponseEntity<Void> deActiveAccount(long id) {
//        var user = getSingleUser(id);
//        user.setEnabled(false);
//        userRepository.save(user);
//        return ResponseEntity.ok().build();
//    }
//
//    public ResponseEntity<String> activeAccount(long id) {
//        var user = getSingleUser(id);
//        user.setEnabled(true);
//        userRepository.save(user);
//        return ResponseEntity.ok("User activated successfully.");
//    }

    public User getSingleUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public ResponseEntity<String> createTeamWithManagerAndMembers(CreateTeamDao createTeamDao) {
        User manager = userRepository.findById(createTeamDao.getManagerId()).
                orElseThrow(() -> new UserNotFoundException("Manager not found."));
        List<User> members = createTeamDao.getMembersId().stream().map(id -> userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Manager not found."))).toList();
        Team team = Team
                .builder()
                .manager(manager)
                .members(members)
                .build();
        teamRepository.save(team);
        return ResponseEntity.ok("Team created!");
    }

    public ResponseEntity<String> updateTeamWithManagerAndMembers(long id, CreateTeamDao createTeamDao) {
        Team team = findTeamById(id);
        User manager = userRepository.findById(createTeamDao.getManagerId())
                .orElseThrow(() -> new UserNotFoundException("Manager not found."));
        List<User> members = createTeamDao.getMembersId().stream().map(userId -> userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Manager not found."))).toList();
        team.setManager(manager);
        team.setMembers(members);
        teamRepository.save(team);
        return ResponseEntity.ok("Updated Team successfully.");
    }

    public ResponseEntity<String> deleteTeamById(long id) {
        Team team = findTeamById(id);
        teamRepository.delete(team);
        return ResponseEntity.ok("Deleted Team successfully.");
    }

    private Team findTeamById(long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found!"));
    }

    public ResponseEntity<Object> getTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDao> teamDaos = teams.stream().map(team ->
                        TeamDao.builder()
                                .id(team.getId())
                                .manager(UserDao.userDaoConverter(team.getManager()))
                                .members(
                                        team.getMembers().stream().map(
                                                UserDao::userDaoConverter
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

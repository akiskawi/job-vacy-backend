package com.manpower.backendProject.services;

import com.manpower.backendProject.auth.AuthenticationResponse;
import com.manpower.backendProject.auth.RegisterRequest;
import com.manpower.backendProject.config.JwtService;
import com.manpower.backendProject.controllers.dao.UpdateUser;
import com.manpower.backendProject.controllers.dao.UserDao;
import com.manpower.backendProject.user.User;
import com.manpower.backendProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseEntity<Object> getUsers(){
        var users = repository.findAll();
        return  ResponseEntity.ok(
                users.stream().map(
                user -> UserDao
                        .builder()
                        .id(user.getId())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .email(user.getEmail())
                        .roles(user.getRoles())
//                        .requests(user.getRequests())
//                        .team(user.getTeam())
//                        .teamManager(user.getTeamManager())
//                        .remainingDays((user.getRemainingDays()))
                        .enabled(user.isEnabled())
                        .build()

        ).toList());
    }

    public ResponseEntity<Object> createUser(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .enabled(true)
                .build();
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already existed");
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var auth = AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();
        return ResponseEntity.ok(auth);
    }

    public ResponseEntity<Object> updateUser(int id, UpdateUser request){
        var user = getSingleUser(id);
        if (request.getFirstname()!=null) user.setFirstname(request.getFirstname());
        if (request.getLastname()!=null) user.setLastname(request.getLastname());
        if (request.getPassword()!=null) user.setPassword(request.getPassword());
        if (request.getRoles()!=null) user.setRoles(request.getRoles());
        repository.save(user);
        return ResponseEntity.ok("User updated!");
    }
    public ResponseEntity<String> deActiveAccount(int id){
        var user = getSingleUser(id);
        user.setEnabled(false);
        repository.save(user);
        return ResponseEntity.ok("Deactivate user with id = " + id);
    }
    public ResponseEntity<String> activeAccount(int id){
        var user = getSingleUser(id);
        user.setEnabled(true);
        repository.save(user);
        return ResponseEntity.ok("Activate user with id = " + id);
    }

    public User getSingleUser(int id){
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }
}

package com.manpower.backendProject.services;

import com.manpower.backendProject.models.dao.RegisterRequest;
import com.manpower.backendProject.models.dao.UpdateUser;
import com.manpower.backendProject.exception.UserAlreadyExistsException;
import com.manpower.backendProject.exception.UserNotFoundException;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.EntityToDaoHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;




    public ResponseEntity<Object> getUsers(){
        var users = repository.findAll();
        return  ResponseEntity.ok(
                users.stream().map(EntityToDaoHelper::userToUserDao).toList());
    }

    public ResponseEntity<String> createUser(RegisterRequest request){
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

    public ResponseEntity<String> updateUser(int id, UpdateUser request){
        var user = getSingleUser(id);
        if (request.getFirstname()!=null) user.setFirstname(request.getFirstname());
        if (request.getLastname()!=null) user.setLastname(request.getLastname());
        if (request.getPassword()!=null) user.setPassword(request.getPassword());
        if (request.getRoles()!=null) user.setRoles(request.getRoles());
        repository.save(user);
        return ResponseEntity.ok("User updated successfully.");
    }
    public ResponseEntity<String> deActiveAccount(int id){
        var user = getSingleUser(id);
        user.setEnabled(false);
        repository.save(user);
        return ResponseEntity.ok("User deactivated successfully.");
    }
    public ResponseEntity<String> activeAccount(int id){
        var user = getSingleUser(id);
        user.setEnabled(true);
        repository.save(user);
        return ResponseEntity.ok("User activated successfully.");
    }

    public User getSingleUser(int id){
        return repository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found."));
    }
}

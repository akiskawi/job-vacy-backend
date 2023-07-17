package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.dao.CreateTeamDao;
import com.manpower.backendProject.models.dao.RegisterRequest;
import com.manpower.backendProject.models.dao.UpdateUser;
import com.manpower.backendProject.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final AdminService service;

    @GetMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        return service.getUsers();
    }

    @PostMapping("users")
    @ResponseBody
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest request) {
        return service.createUser(request);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UpdateUser request) {
        return service.updateUser(id, request);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> DeActivateUser(@PathVariable int id) {
        return service.deActiveAccount(id);
    }

    @PostMapping("users/{id}")
    public ResponseEntity<String> activateUser(@PathVariable int id) {
        return service.activeAccount(id);
    }

    @PostMapping("team")
    public ResponseEntity<String> createTeamWithManagerAndMembers(@RequestBody CreateTeamDao createTeamDao) {
        return service.createTeamWithManagerAndMembers(createTeamDao);
    }

    @PutMapping("team/{id}")
    public ResponseEntity<String> updateTeamWithManagerAndMembers(@PathVariable int id,@RequestBody CreateTeamDao createTeamDao) {
        return service.updateTeamWithManagerAndMembers(id,createTeamDao);
    }
    @DeleteMapping("team/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id){
        return service.deleteTeamById(id);
    }

    @GetMapping("team")
    public ResponseEntity<Object> getAllTeams(){
        return service.getTeams();
    }

    @GetMapping("leaverequests")
    public ResponseEntity<Object> getAllLeaveRequests() {
        return service.getRequests();
    }

}

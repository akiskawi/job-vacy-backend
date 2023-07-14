package com.manpower.backendProject.controllers;

import com.manpower.backendProject.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/manager")
public class ManagerController {

    private final ManagerService service;
    @GetMapping("users")
    public ResponseEntity<Object> getTeamMembers(@RequestBody Integer team_id){
        return service.getTeamMembers(team_id);
    }

    @GetMapping("test")
    public ResponseEntity<String> test(@RequestBody Integer test){
        return ResponseEntity.ok("test: " + test);
    }
}

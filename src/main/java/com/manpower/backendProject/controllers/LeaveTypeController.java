package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.leave_types.LeaveType;
import com.manpower.backendProject.repositories.LeaveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class LeaveTypeController {

    private final LeaveTypeRepository repository;

    @GetMapping("leavetypes")
    public ResponseEntity<List<LeaveType>> getLeaveTypes() {
        Map<String, String> leaveTypes = new HashMap<>();
        return ResponseEntity.ok(repository.findAll());
    }
}

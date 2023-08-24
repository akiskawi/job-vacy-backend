package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.leave.LeaveRequestTYPE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class EnumController {

    @GetMapping("leavetypes")
    public ResponseEntity<Map<String, String>> getLeaveTypes() {
        Map<String, String> leaveTypes = new HashMap<>();

        for (LeaveRequestTYPE type : LeaveRequestTYPE.values()) {
            leaveTypes.put(type.name(), type.getLabel());
        }
        return ResponseEntity.ok(leaveTypes);
    }
}

package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.dao.LeaveRequestDao;
import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping("{id}/requests")
    public ResponseEntity<Object> getAllLeaveRequests(@PathVariable int id) {
        return service.getUsersLeaveRequests(id);
    }

    @GetMapping("{user_id}/requests/{request_id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable int user_id, @PathVariable int request_id) {
        return service.getLeaveRequestById(user_id, request_id);
    }

    @PostMapping("{id}/requests")
    @ResponseBody
    public ResponseEntity<String> createLeave(@PathVariable int id, @RequestBody LeaveRequestDao leave) {
        return service.createLeaveRequest(id, leave);
    }
}

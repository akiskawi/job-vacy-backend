package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.leave.LeaveRequestDao;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDaysDao;
import com.manpower.backendProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService service;

    @GetMapping("requests")
    public ResponseEntity<Object> getAllLeaveRequests(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return service.getUserRequests(pageNo - 1, pageSize, sortBy);
    }

//    @PutMapping("password")
//    public ResponseEntity<String> changePassword(@RequestBody ResetPassword resetPassword) {
//        return service.changePassword(resetPassword);
//    }
//
//    @PostMapping("password")
//    public ResponseEntity<String> resetPassword(@RequestBody String newPassword) {
//        return service.resetPassword(newPassword);
//    }

    @PostMapping("requests")
    public ResponseEntity<String> createRequest(@RequestBody LeaveRequestDao request) {
        return service.createRequest(request);
    }

    @PutMapping("requests/{id}")
    public ResponseEntity<String> updateRequest(@PathVariable long id, @RequestBody LeaveRequestDao request) {
        return service.updateRequest(id, request);
    }

    @DeleteMapping("requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable long id) {
        return service.deleteRequest(id);
    }

    @GetMapping("days")
    public ResponseEntity<List<LeaveRequestAvailableDaysDao>> getAllRemainingDays() {
        return service.getRemainingLeaveDays();
    }

//    @GetMapping("days/{type}")
//    public ResponseEntity<List<LeaveRequestAvailableDaysDao>> getAllRemainingDaysByType(@PathVariable LeaveRequestTYPE type) {
//        return service.getRemainingLeaveDaysByType(type);
//    }


}

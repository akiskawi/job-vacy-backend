package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.leave.LeaveRequestDao;
import com.manpower.backendProject.models.team.TeamDao;
import com.manpower.backendProject.models.user.UserDao;
import com.manpower.backendProject.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/manager/")
@CrossOrigin(origins = "http://localhost:3000")
public class ManagerController {

    private final ManagerService service;

    @GetMapping
    public ResponseEntity<TeamDao> getManagerTeam() {
        return service.getManagerTeam();
    }
    @GetMapping({"members","members/"})
    public ResponseEntity<Object> getManagerTeamMembers(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return service.getManagerTeamMembers(pageNo - 1, pageSize, sortBy);
    }
    @GetMapping("members/{id}")
    public ResponseEntity<List<LeaveRequestDao>> getMemberLeaveRequests(@PathVariable long id){
        return service.getMemberRequests(id);
    }
    @PostMapping("members/{userId}/request/{requestId}")
    public ResponseEntity<String> evaluateRequest(@PathVariable long userId,@PathVariable long requestId,@RequestBody boolean flag){
        return service.evaluateRequest(userId,requestId,flag);
    }

    @GetMapping("requests")
    public ResponseEntity<Object> getAllTeamRequests(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return service.getAllTeamMembersRequests(pageNo - 1, pageSize, sortBy);
    }


}

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
@RequestMapping("api/v1/manager")
// Mapping for the methods are subject to change when I talk to the frontend developer!
public class ManagerController {

    private final ManagerService service;

    @GetMapping("/")
    public ResponseEntity<TeamDao> getManagerTeam() {
        return service.getManagerTeam();
    }
    @GetMapping({"members","members/"})
    public ResponseEntity<List<UserDao>> getManagerTeamMembers(){
        return service.getManagerTeamMembers();
    }
    @GetMapping("members/{id}")
    public ResponseEntity<List<LeaveRequestDao>> getMemberLeaveRequests(@PathVariable long id){
        return service.getMemberRequests(id);
    }
    @PostMapping("members/{userId}/request/{requestId}")
    public ResponseEntity<String> evaluateRequest(@PathVariable long userId,@PathVariable long requestId,@RequestBody boolean flag){
        return service.evaluateRequest(userId,requestId,flag);
    }


}

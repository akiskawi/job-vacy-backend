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

    /**
     *
     * @return A list of all the users
     */
    @GetMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        return service.getUsers();
    }

    /**
     * <p>Create a new user and return it</p>
     * @param request = info about a new user
     * @return Success message
     */
    @PostMapping("users")
    @ResponseBody
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest request) {
        return service.createUser(request);
    }

    /**
     *
     * @param id id of the user to update
     * @param request updated details of the user
     * @return Success message
     */
    @PutMapping("users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UpdateUser request) {
        return service.updateUser(id, request);
    }


    /**
     * <p>Disable a user's account</p>
     * @param id id of the user to disable
     * @return Success message
     */
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> DeActivateUser(@PathVariable int id) {
        return service.deActiveAccount(id);
    }

    /**
     * <p>Enable a user's account</p>
     * @param id id of the user to enable
     * @return Success message
     */
    @PostMapping("users/{id}")
    public ResponseEntity<String> activateUser(@PathVariable int id) {
        return service.activeAccount(id);
    }

    /**
     * <p>Create a new team with users as members and a user with role manager as a manager.</p>
     * @param createTeamDao
     * @return Success message
     */
    @PostMapping("team")
    public ResponseEntity<String> createTeamWithManagerAndMembers(@RequestBody CreateTeamDao createTeamDao) {
        return service.createTeamWithManagerAndMembers(createTeamDao);
    }

    /**
     * <p>Update a team</p>
     * @param id the id of the team to update
     * @param createTeamDao
     * @return Success message
     */
    @PutMapping("team/{id}")
    public ResponseEntity<String> updateTeamWithManagerAndMembers(@PathVariable int id,@RequestBody CreateTeamDao createTeamDao) {
        return service.updateTeamWithManagerAndMembers(id,createTeamDao);
    }

    /**
     * <p>Delete a team</p>
     * @param id the id of the team to delete
     * @return Success message
     */
    @DeleteMapping("team/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id){
        return service.deleteTeamById(id);
    }

    /**
     * <p>Returns all teams and their manager and members info</p>
     * @return
     */
    @GetMapping("team")
    public ResponseEntity<Object> getAllTeams(){
        return service.getTeams();
    }

    /**
     * <p>Returns all leave requests</p>
     * @return all leave requests
     */
    @GetMapping("leaverequests")
    public ResponseEntity<Object> getAllLeaveRequests() {
        return service.getRequests();
    }

}

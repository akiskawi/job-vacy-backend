package com.manpower.backendProject.controllers.admin;

import com.manpower.backendProject.models.team.CreateTeamDao;
import com.manpower.backendProject.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Administrator Access", description="Endpoints available only to admin role")
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/team")
public class AdminTeamController {
    private final AdminService service;

    /**
     * <p>Create a new team with users as members and a user with role manager as a manager.</p>
     * @param createTeamDao
     * @return Success message
     */
    @PostMapping("/")
    public ResponseEntity<String> createTeamWithManagerAndMembers(@RequestBody CreateTeamDao createTeamDao) {
        return service.createTeamWithManagerAndMembers(createTeamDao);
    }
    /**
     * <p>Update a team</p>
     * @param id the id of the team to update
     * @param createTeamDao
     * @return Success message
     */
    @PutMapping("{id}")
    public ResponseEntity<String> updateTeamWithManagerAndMembers(@PathVariable long id,@RequestBody CreateTeamDao createTeamDao) {
        return service.updateTeamWithManagerAndMembers(id,createTeamDao);
    }
    /**
     * <p>Delete a team</p>
     * @param id the id of the team to delete
     * @return Success message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable long id){
        return service.deleteTeamById(id);
    }

    /**
     * <p>Returns all teams and their manager and members info</p>
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<Object> getAllTeams(){
        return service.getTeams();
    }
}

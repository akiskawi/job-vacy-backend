package com.manpower.backendProject.controllers.admin;

import com.manpower.backendProject.models.team.CreateTeamDao;
import com.manpower.backendProject.models.auth.RegisterRequest;
import com.manpower.backendProject.models.user.UpdateUser;
import com.manpower.backendProject.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Administrator Access", description="Endpoints available only to admin role")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final AdminService service;

    /**
     * <p>Returns all leave requests</p>
     * @return all leave requests
     */
    @GetMapping("leaverequests")
    public ResponseEntity<Object> getAllLeaveRequests() {
        return service.getRequests();
    }

}

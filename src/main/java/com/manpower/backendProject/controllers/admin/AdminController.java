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
//
//    /**
//     *
//     * @return A list of all the users for this page.
//     */
//    @GetMapping("users")
//    public ResponseEntity<Object> getAllUsers(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "0") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortBy
//    ) {
//        return service.getUsers(pageNo, pageSize, sortBy);
//    }
//
//    /**
//     * <p>Create a new user and return it</p>
//     * @param request = info about a new user
//     * @return Success message
//     */
//    @PostMapping("users")
//    @ResponseBody
//    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest request) {
//        return service.createUser(request);
//    }
//
//    /**
//     *
//     * @param id id of the user to update
//     * @param request updated details of the user
//     * @return Success message
//     */
//    @PutMapping("users/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UpdateUser request) {
//        return service.updateUser(id, request);
//    }
//
//    /**
//     * <p>Disable a user's account</p>
//     * @param id id of the user to disable
//     * @return Success message
//     */
//    @DeleteMapping("users/{id}")
//    public ResponseEntity<String> DeActivateUser(@PathVariable long id) {
//        return service.deActiveAccount(id);
//    }
//
//    /**
//     * <p>Enable a user's account</p>
//     * @param id id of the user to enable
//     * @return Success message
//     */
//    @PostMapping("users/{id}")
//    public ResponseEntity<String> activateUser(@PathVariable long id) {
//        return service.activeAccount(id);
//    }

    /**
     * <p>Returns all leave requests</p>
     * @return all leave requests
     */
    @GetMapping("leaverequests")
    public ResponseEntity<Object> getAllLeaveRequests() {
        return service.getRequests();
    }

}

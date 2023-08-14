package com.manpower.backendProject.controllers.admin;

import com.manpower.backendProject.models.auth.RegisterRequest;
import com.manpower.backendProject.models.user.UpdateUser;
import com.manpower.backendProject.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/users")
@Tag(name = "Administrator Access", description="Endpoints available only to admin role")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserController{
    private final AdminService service;

    /**
     *
     * @return A list of all the users for this page.
     */
    @GetMapping
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return service.getUsers(pageNo - 1, pageSize, sortBy);
    }

    /**
     * <p>Create a new user and return it</p>
     * @param request = info about a new user
     * @return Success message
     */
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createUser(@Valid @RequestBody RegisterRequest request) {
        return service.createUser(request);
    }

    /**
     *
     * @param id id of the user to update
     * @param request updated details of the user
     * @return Success message
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody UpdateUser request) {
        return service.updateUser(id, request);
    }

    /**
     * <p>Disable a user's account</p>
     * @param id id of the user to disable
     * @return Success message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> DeActivateUser(@PathVariable long id) {
        return service.deActiveAccount(id);
    }

    /**
     * <p>Enable a user's account</p>
     * @param id id of the user to enable
     * @return Success message
     */
    @PostMapping("{id}")
    public ResponseEntity<String> activateUser(@PathVariable long id) {
        return service.activeAccount(id);
    }

}
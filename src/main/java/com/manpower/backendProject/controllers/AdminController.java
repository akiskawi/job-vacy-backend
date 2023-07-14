package com.manpower.backendProject.controllers;

import com.manpower.backendProject.auth.RegisterRequest;
import com.manpower.backendProject.controllers.dao.UpdateUser;
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

    @GetMapping("users")
    public ResponseEntity<Object> getAllUsers() {
        return service.getUsers();
    }

    @PostMapping("users")
    @ResponseBody
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest request) {
        return service.createUser(request);
    }

    // TODO: xreiazetai to edit? afou einai diaforetiko request apo to delete,
    //  an einai etsi na to kanoume: /{id}/edit, /{id}/delete, /{id}/enable klp kalutera?
    @PutMapping("users/edit/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UpdateUser request) {
        return service.updateUser(id, request);
    }

    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<String> DeActivateUser(@PathVariable int id) {
       return service.deActiveAccount(id);
    }

    @PostMapping("users/enable/{id}")
    public ResponseEntity<String> activateUser(@PathVariable int id) {
        return service.activeAccount(id);
    }
}

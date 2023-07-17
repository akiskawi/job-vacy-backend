package com.manpower.backendProject.controllers;

import com.manpower.backendProject.models.dao.AuthenticationRequest;
import com.manpower.backendProject.models.dao.AuthenticationResponse;
import com.manpower.backendProject.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("auth/login")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
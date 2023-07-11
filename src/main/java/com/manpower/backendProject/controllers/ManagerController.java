package com.manpower.backendProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/manager")
public class ManagerController {
    public ResponseEntity<String> sayHelloManager(){
        return ResponseEntity.ok("manager");
    }
}

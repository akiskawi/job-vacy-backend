package com.manpower.backendProject.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @GetMapping("/api/v1/secure")
    public ResponseEntity<String> secure(){
        return ResponseEntity.ok("Secure endpoint!");
    }

    @GetMapping("/test")
    public String notSecure(){
        return "Rest API working";
    }
}

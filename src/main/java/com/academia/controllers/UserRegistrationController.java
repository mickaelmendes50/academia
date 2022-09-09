package com.academia.controllers;

import com.academia.dto.UserRegistrationDTO;
import com.academia.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService service;

    @PostMapping("/api/register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO user) throws Exception {
        return ResponseEntity.ok(service.registerUser(user));
    }
}

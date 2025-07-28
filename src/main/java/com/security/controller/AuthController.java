package com.security.controller;


import com.security.dto.AuthRequest;
import com.security.dto.AuthResponse;
import com.security.dto.RegisterRequest;
import com.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        return authService.login(request);
    }


}

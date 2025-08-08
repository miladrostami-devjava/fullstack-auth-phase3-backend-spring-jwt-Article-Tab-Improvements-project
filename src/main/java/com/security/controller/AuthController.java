package com.security.controller;


import com.security.dto.AuthRequest;
import com.security.dto.AuthResponse;
import com.security.dto.RegisterRequest;
import com.security.service.AuthService;
import com.security.utils.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


   /* @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {

        authService.register(request);
    }*/

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (request.email() == null || request.email().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }



    /*@PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {

        return authService.login(request);
    }
*/

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
if (!ValidationUtils.isValidPassword(request.password())){
    throw new IllegalArgumentException("Password does not meet criteria");
}
        return authService.login(request);
    }


}

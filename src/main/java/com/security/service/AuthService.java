package com.security.service;


import com.security.config.JwtService;
import com.security.dto.AuthRequest;
import com.security.dto.AuthResponse;
import com.security.dto.RegisterRequest;
import com.security.entity.User;
import com.security.repository.UserRepository;

import com.security.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;


    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }


    public void register(RegisterRequest registerRequest) {
      /*  var user = User.builder()
                .username(registerRequest.username())
                .password(encoder.encode(registerRequest.password()))
                .role("USER")
                .build();*/


        if (!ValidationUtils.isValidEmail(registerRequest.email())){
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!ValidationUtils.isValidPassword(registerRequest.password())){
            throw new IllegalArgumentException("Invalid  Password");
        }

        var user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(encoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        user.setRole("USER");
        userRepository.save(user);



    }


    public AuthResponse login(AuthRequest request) {
        if (!ValidationUtils.isValidPassword(request.password())){
            throw new IllegalArgumentException("Invalid Password");
        }
        var user = userRepository.findByUsername(request.username()).orElseThrow(() -> new RuntimeException("Invalid Username"));
        if (!encoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(request.username());
        return new AuthResponse(token);
    }


}

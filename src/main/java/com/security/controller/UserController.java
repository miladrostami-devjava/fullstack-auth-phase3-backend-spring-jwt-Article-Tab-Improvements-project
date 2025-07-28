package com.security.controller;

import com.security.dto.UserDto;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile(Authentication authentication) {
        String username = authentication.getName();
        UserDto userDto = userService.getProfileByUsername(username);
        return ResponseEntity.ok(userDto);
    }
}

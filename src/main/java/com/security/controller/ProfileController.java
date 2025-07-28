package com.security.controller;

import com.security.dto.UserDto;

import com.security.dto.profile.ChangePasswordRequest;
import com.security.dto.profile.UpdateProfileRequest;
import com.security.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")

public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getProfile(Authentication auth) {
        return ResponseEntity.ok(profileService.getProfile(auth.getName()));
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(Authentication auth, @RequestBody UpdateProfileRequest request) {
        profileService.updateProfile(auth.getName(), request);
        return ResponseEntity.ok("Updated");
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(Authentication auth, @RequestBody ChangePasswordRequest request) {
        boolean result = profileService.changePassword(auth.getName(), request);
        return result ? ResponseEntity.ok("Password updated") :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect current password");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccount(Authentication auth) {
        profileService.deleteAccount(auth.getName());
        return ResponseEntity.ok("Account deleted");
    }

    @PostMapping("/photo")
    public ResponseEntity<String> uploadPhoto(Authentication auth, @RequestParam("file") MultipartFile file) throws IOException {
        profileService.uploadPhoto(auth.getName(), file);
        return ResponseEntity.ok("Photo uploaded");
    }
}

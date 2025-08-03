package com.security.controller;

import com.security.dto.UserDto;

import com.security.dto.profile.ChangePasswordRequest;
import com.security.dto.profile.UpdateProfileRequest;
import com.security.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<String> updateProfile(Authentication auth, @RequestBody UpdateProfileRequest request) {
        System.out.println("AUTH: " + auth.getName()); // این باید نام کاربری یا ایمیل درست رو چاپ کنه

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

    /*@GetMapping("/photo/{filename:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/uploads";
        File file = new File(uploadDir, filename);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(file.toURI());
        String contentType = Files.probeContentType(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(resource);
    }*/


  /*  @GetMapping("/api/profile/photo/{filename:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir") + "/uploads").resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG) // یا .IMAGE_PNG بسته به نوع عکس
//                .body(resource);

        String contentType = Files.probeContentType(filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);

    }*/
/*  @GetMapping("/api/profile/photo/{filename:.+}")
  public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
      Path filePath = Paths.get(System.getProperty("user.dir") + "/uploads").resolve(filename);
      Resource resource = new UrlResource(filePath.toUri());

      if (!resource.exists() || !resource.isReadable()) {
          return ResponseEntity.notFound().build();
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
          contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(contentType))
              .body(resource);
  }*/
  @GetMapping("/photo/{filename:.+}")
  public ResponseEntity<Resource> getPhoto(@PathVariable String filename) throws IOException {
      Path filePath = Paths.get(System.getProperty("user.dir"), "uploads", filename);
      Resource resource = new UrlResource(filePath.toUri());

      if (!resource.exists() || !resource.isReadable()) {
          return ResponseEntity.notFound().build();
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
          contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(contentType))
              .body(resource);
  }




}

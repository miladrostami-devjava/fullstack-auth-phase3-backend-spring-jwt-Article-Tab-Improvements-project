package com.security.service;

import com.security.dto.UserDto;
import com.security.dto.profile.ChangePasswordRequest;
import com.security.dto.profile.UpdateProfileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {
    UserDto getProfile(String email);
    void updateProfile(String email, UpdateProfileRequest request);
    boolean changePassword(String email, ChangePasswordRequest request);
    void deleteAccount(String email);
    void uploadPhoto(String email, MultipartFile file) throws IOException;
}
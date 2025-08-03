package com.security.service;

import com.security.dto.UserDto;
import com.security.dto.profile.ChangePasswordRequest;
import com.security.dto.profile.UpdateProfileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {
    UserDto getProfile(String username);
    void updateProfile(String username, UpdateProfileRequest request);
    boolean changePassword(String username, ChangePasswordRequest request);
    void deleteAccount(String username);
    void uploadPhoto(String username, MultipartFile file) throws IOException;
}
package com.security.service;



import com.security.dto.UserDto;

import com.security.dto.profile.ChangePasswordRequest;
import com.security.dto.profile.UpdateProfileRequest;
import com.security.entity.User;
import com.security.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service

public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDto(user.getUsername(), user.getEmail(), user.getRole(), user.getProfilePhoto());
    }

    @Override
    public void updateProfile(String email, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);
    }

    @Override
    public boolean changePassword(String email, ChangePasswordRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteAccount(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public void uploadPhoto(String email, MultipartFile file) throws IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String filePath = "uploads/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        user.setProfilePhoto(filePath);
        userRepository.save(user);
    }
}

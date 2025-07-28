package com.security.service;

import com.security.dto.UserDto;
import com.security.entity.User;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getProfilePhoto() // اگر در User entity داری
        );
    }
}
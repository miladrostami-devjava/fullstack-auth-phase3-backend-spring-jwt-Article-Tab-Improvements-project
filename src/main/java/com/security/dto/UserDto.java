package com.security.dto;


import java.util.Objects;

public class UserDto {
    private String username;
    private String email;
    private String role;
    private String profilePhoto;


    public UserDto() {
    }

    public UserDto(String username, String email, String role, String profilePhoto) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.profilePhoto = profilePhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserDto userDto)) return false;
        return Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(role, userDto.role) && Objects.equals(profilePhoto, userDto.profilePhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, role, profilePhoto);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}


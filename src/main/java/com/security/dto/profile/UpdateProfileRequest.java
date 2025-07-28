package com.security.dto.profile;

import java.util.Objects;

public class UpdateProfileRequest {
    private String username;
    private String email;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String username, String email) {
        this.username = username;
        this.email = email;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UpdateProfileRequest that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }

    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
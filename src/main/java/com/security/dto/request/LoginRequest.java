package com.security.dto.request;


import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof LoginRequest that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), password);
    }
}

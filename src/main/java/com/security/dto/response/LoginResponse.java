package com.security.dto.response;

import java.util.Objects;

public class LoginResponse {


    private String accessToken;


    public LoginResponse() {
    }

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof LoginResponse that)) return false;
        return Objects.equals(getAccessToken(), that.getAccessToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken());
    }
}

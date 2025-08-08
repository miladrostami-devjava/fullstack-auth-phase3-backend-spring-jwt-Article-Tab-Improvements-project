package com.security.entity;

import jakarta.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    private String profilePhoto;
    private String role;


    public User() {
    }

    public User(String username, String password, String email, String profilePhoto, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.role = role;
    }

    public User(Long id, String username, String password, String email, String profilePhoto, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}

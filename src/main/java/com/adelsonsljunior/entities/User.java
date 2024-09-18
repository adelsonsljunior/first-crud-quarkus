package com.adelsonsljunior.entities;

import com.adelsonsljunior.dtos.UserRequestDTO;
import com.adelsonsljunior.dtos.UserResponseDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public User() {

    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User(UserRequestDTO data) {
        this.username = data.username();
        this.email = data.email();
        this.password = data.password();
    }

    public UserResponseDTO toResponseDTO() {
        return new UserResponseDTO(
                this.id,
                this.username,
                this.email,
                this.createdAt,
                this.updatedAt
        );
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }
}

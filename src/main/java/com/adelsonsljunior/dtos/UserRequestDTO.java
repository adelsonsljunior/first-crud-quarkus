package com.adelsonsljunior.dtos;

public record UserRequestDTO(
        String username,
        String email,
        String password) {
}

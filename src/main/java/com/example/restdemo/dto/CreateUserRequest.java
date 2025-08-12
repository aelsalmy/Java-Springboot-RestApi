package com.example.restdemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank(message = "Enter a Name") String name,
        @Email(message = "Invalid Email Address") String email,
        @NotBlank(message = "Enter a Department") String department
){}

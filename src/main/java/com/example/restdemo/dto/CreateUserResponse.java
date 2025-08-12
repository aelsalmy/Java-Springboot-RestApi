package com.example.restdemo.dto;

import java.time.LocalDateTime;

public record CreateUserResponse(
    Long id,
    String name,
    String email,
    String department,
    LocalDateTime createdAt
){}

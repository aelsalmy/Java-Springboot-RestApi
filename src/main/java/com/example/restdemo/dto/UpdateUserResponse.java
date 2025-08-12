package com.example.restdemo.dto;

import java.time.LocalDateTime;

public record UpdateUserResponse(
    Long id,
    String name,
    String email,
    String department,
    LocalDateTime updatedAt
){}

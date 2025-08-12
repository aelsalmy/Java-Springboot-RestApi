package com.example.restdemo.dto;

public record ErrorResponse(
   int status,
   String message,
   long timestamp
) {}

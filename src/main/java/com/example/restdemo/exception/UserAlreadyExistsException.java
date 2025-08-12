package com.example.restdemo.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
      super("User with email " + " already Exists");
    }
}

package com.example.restdemo.exception;

import com.example.restdemo.dto.ErrorResponse;
import com.example.restdemo.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException exc){
        ErrorResponse resp = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(resp , HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> userExists(UserAlreadyExistsException exc){

        System.out.println("Valid Error 2");

        ErrorResponse resp = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.badRequest().body(resp);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> failedValidation(ConstraintViolationException exc){

        System.out.println("Valid Error Detected");

        ErrorResponse resp = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getConstraintViolations().stream().findFirst()
                        .map(ConstraintViolation::getMessage).orElse("Validation Error"),
                System.currentTimeMillis()
        );

        return ResponseEntity.badRequest().body(resp);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> failedDtoValidation(MethodArgumentNotValidException exc){

        System.out.println("Valid Error Detected");

        ErrorResponse resp = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity.badRequest().body(resp);
    }
}

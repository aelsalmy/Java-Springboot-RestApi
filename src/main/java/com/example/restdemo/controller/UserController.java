package com.example.restdemo.controller;

import com.example.restdemo.dto.CreateUserRequest;
import com.example.restdemo.dto.CreateUserResponse;
import com.example.restdemo.dto.UpdateUserResponse;
import com.example.restdemo.dto.UserResponse;
import com.example.restdemo.entity.User;
import com.example.restdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {

        User user = userService.getUser(id);

        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDepartment()
        ));
    }

    @PostMapping("/user/addUser")
    public ResponseEntity<CreateUserResponse> addUser(@Valid @RequestBody CreateUserRequest req){

        CreateUserResponse resp = userService.addUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);

    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> resp = userService.getAllUsers();

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody CreateUserRequest req
    ){
        UpdateUserResponse resp = userService.updateUser(req , id);

        return ResponseEntity.ok(resp);
    }
}

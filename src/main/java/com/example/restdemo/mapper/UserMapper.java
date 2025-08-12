package com.example.restdemo.mapper;

import com.example.restdemo.dto.UserResponse;
import com.example.restdemo.entity.User;
import com.example.restdemo.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toDto(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDepartment()
        );
    }

    public User toEntity(UserResponse userDto){
        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setDepartment(userDto.department());
        return user;
    }
}

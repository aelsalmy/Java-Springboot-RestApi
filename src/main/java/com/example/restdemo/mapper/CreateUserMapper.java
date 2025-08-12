package com.example.restdemo.mapper;

import com.example.restdemo.dto.CreateUserResponse;
import com.example.restdemo.dto.UpdateUserResponse;
import com.example.restdemo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMapper {
    public CreateUserResponse createToDto(User user){
        return new CreateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDepartment(),
                user.getCreatedAt()
        );
    }

    public UpdateUserResponse updateToDto(User user){
        return new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDepartment(),
                user.getUpdatedAt()
        );
    }

}

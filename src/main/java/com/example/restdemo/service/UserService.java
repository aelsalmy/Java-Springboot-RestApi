package com.example.restdemo.service;

import com.example.restdemo.dto.CreateUserRequest;
import com.example.restdemo.dto.CreateUserResponse;
import com.example.restdemo.dto.UpdateUserResponse;
import com.example.restdemo.dto.UserResponse;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.UserAlreadyExistsException;
import com.example.restdemo.exception.UserNotFoundException;
import com.example.restdemo.mapper.CreateUserMapper;
import com.example.restdemo.mapper.UserMapper;
import com.example.restdemo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CreateUserMapper createUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User getUser(Long userId){
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public CreateUserResponse addUser(CreateUserRequest dto){
        if(userRepo.existsByEmail(dto.email())){
            throw new UserAlreadyExistsException(dto.email());
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setDepartment(dto.department());

        User savedUser = userRepo.save(user);

        return createUserMapper.createToDto(savedUser);
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        users.forEach(
                (user) -> userResponses.add(userMapper.toDto(user))
        );

        return userResponses;
    }

    @Transactional
    public void deleteUser(Long userId){
        User user = this.getUser(userId);
        userRepo.delete(user);
    }

    @Transactional
    public UpdateUserResponse updateUser(CreateUserRequest dto , Long userId){
        User user = this.getUser(userId);

        if(dto.name() != null){
            user.setName(dto.name());
        }

        if(dto.email() != null){
            if(userRepo.existsByEmail(dto.email())){
                throw new UserAlreadyExistsException(dto.email());
            }
            user.setEmail(dto.email());
        }

        if(dto.department() != null){
            user.setDepartment(dto.department());
        }

        return createUserMapper.updateToDto(user);
    }
}

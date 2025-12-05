package com.fitness.userservice.services;

import com.fitness.userservice.DTO.RegisterRequest;
import com.fitness.userservice.DTO.UserResponse;
import com.fitness.userservice.entity.User;
import com.fitness.userservice.repositary.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    private final UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }


    public  UserResponse register(RegisterRequest registerRequest) {

        if(repository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email already exist");
        }

        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(registerRequest.getPassword());

        User savedUser=repository.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        userResponse.setPassword(savedUser.getPassword());


        return userResponse;
    }

    public  UserResponse getUserProfile(String userId) {
        User userProile=repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse userResponse=new UserResponse();
        userResponse.setId(userProile.getId());
        userResponse.setFirstName(userProile.getFirstName());
        userResponse.setLastName(userProile.getLastName());
        userResponse.setEmail(userProile.getEmail());
        userResponse.setPassword(userProile.getPassword());
        userResponse.setCreatedAt(userProile.getCreatedAt());
        userResponse.setUpdatedAt(userProile.getUpdatedAt());


        return userResponse;




    }

    public List<UserResponse> getAllUsers() {
        List<User> users=repository.findAll();
        List<UserResponse> userResponses=new ArrayList<>();

        for(User user:users){
            UserResponse userResponse=new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(user.getPassword());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());
            userResponses.add(userResponse);
        }
        return userResponses;

    }
}

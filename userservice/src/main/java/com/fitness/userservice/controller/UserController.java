package com.fitness.userservice.controller;


import com.fitness.userservice.DTO.RegisterRequest;
import com.fitness.userservice.DTO.UserResponse;
import com.fitness.userservice.entity.User;
import com.fitness.userservice.services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userServices;



    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userServices.register(registerRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
return ResponseEntity.ok(userServices.getUserProfile(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        List<UserResponse> allUser=userServices.getAllUsers();
        return ResponseEntity.ok(allUser);
    }





}

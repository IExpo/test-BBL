package com.example.testbbl.controller;

import com.example.testbbl.entity.UserEntity;
import com.example.testbbl.exception.NotFoundResourceException;
import com.example.testbbl.model.UserResponse;
import com.example.testbbl.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ManageUserController {

    private final UserRepository userRepository;

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserEntity request) {
        log.info("start create user");
        userRepository.save(request);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,@Valid @RequestBody UserEntity request) {
        userRepository.findById(userId);
        Optional<UserEntity> response = userRepository.findById(userId);
        if (response.isEmpty()){
            throw new NotFoundResourceException("Not found user");
        }

        log.info("start update user");
        userRepository.save(request);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) {
        userRepository.findById(userId);
        Optional<UserEntity> response = userRepository.findById(userId);
        if (response.isEmpty()){
            throw new NotFoundResourceException("Not found user");
        }

        log.info("delete user");
        userRepository.deleteById(userId);
        return ResponseEntity.status(200).build();
    }
}

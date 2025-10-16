package com.example.testbbl.controller;

import com.example.testbbl.entity.UserEntity;
import com.example.testbbl.exception.NotFoundResourceException;
import com.example.testbbl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GetUserController {

    private final UserRepository userRepository;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserEntity>> getListUser() {
        log.info("start get list user");
        List<UserEntity> response = userRepository.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId) {
        log.info("start get user {}", userId);
        Optional<UserEntity> response = userRepository.findById(userId);
        if (response.isEmpty()){
            throw new NotFoundResourceException("Not found user");
        }

        return ResponseEntity.ok(response.get());
    }
}

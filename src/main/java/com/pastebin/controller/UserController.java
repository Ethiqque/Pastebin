package com.example.demo.controller;

import com.pastebin.model.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }
    
    @GetMapping("/user/{userId}")
    public UserDto getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }
    
    @PutMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable("userId") long userId,
                                @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
    
    @DeleteMapping("/user/{userId}")
    public UserDto deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }
}
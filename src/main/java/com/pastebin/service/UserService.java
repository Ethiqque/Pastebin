package com.example.demo.service;

import com.pastebin.model.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(long userId);

    UserDto updateUser(long userId, UserDto userDto);

    UserDto deleteUser(long userId);
}

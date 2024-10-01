package com.example.demo.service.impl;

import com.pastebin.model.dto.UserDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.pastebin.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.pastebin.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userValidator.validateCreate(userDto);

        User user = userMapper.toEntity(userDto);

        userRepository.save(user);
        log.info("Saved user with id {}", user.getId());
        return userDto;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(long userId) {
        return userMapper.toDto(findById(userId));
    }

    @Override
    @Transactional
    public UserDto updateUser(long userId, UserDto userDto) {

        User userToUpdate = findById(userId);

        userValidator.validateUpdate(userDto);

        userMapper.update(userDto, userToUpdate);
        userRepository.save(userToUpdate);
        log.info("Updated user with id {}", userId);
        return userMapper.toDto(userToUpdate);
    }

    @Override
    @Transactional
    public UserDto deleteUser(long userId) {

        User User = findById(userId);
        UserDto UserToDelete = userMapper.toDto(User);

        userRepository.deleteById(userId);
        log.info("Deleted user with id {}", userId);
        return UserToDelete;
    }

    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId +" not found"));
    }
}
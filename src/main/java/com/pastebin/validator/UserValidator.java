package com.pastebin.validator;

import com.pastebin.model.dto.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateCreate(UserDto userDto) {
        validateSomething(userDto);
    }

    public void validateUpdate(UserDto userDto) {
        validateSomething(userDto);
        validateAnotherThing(userDto);
    }

    private void validateSomething(UserDto userDto) {}

    private void validateAnotherThing(UserDto userDto) {}
}
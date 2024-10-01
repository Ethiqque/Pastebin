package com.example.demo.mapper;

import com.pastebin.model.dto.UserDto;
import com.pastebin.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    void update(UserDto dto, @MappingTarget User user);
}
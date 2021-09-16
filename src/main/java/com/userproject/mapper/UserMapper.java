package com.userproject.mapper;

import com.userproject.dto.UserDto;
import com.userproject.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    List<UserDto> userDtos(List<User> users);

    User toUser(UserDto  userDto);
}

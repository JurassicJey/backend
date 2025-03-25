package com.example.spotify.Users.DataMapper;

import com.example.spotify.Users.DataAccess.User;
import com.example.spotify.Users.Presentation.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Important:  Makes this a Spring bean
public interface UserResponseMapper {

    UserResponseDTO userToUserResponseDTO(User user);
}
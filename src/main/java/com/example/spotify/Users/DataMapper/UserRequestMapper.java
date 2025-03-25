package com.example.spotify.Users.DataMapper;

import com.example.spotify.Users.DataAccess.User;
import com.example.spotify.Users.Presentation.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Important:  Makes this a Spring bean
public interface UserRequestMapper {

    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

}
package com.example.spotify.Users.BusinessLogic;



import com.example.spotify.Users.DataAccess.User;
import com.example.spotify.Users.Presentation.UserRequestDTO;
import com.example.spotify.Users.Presentation.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO findUserById(Integer userId);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Integer userId);
    UserResponseDTO updateUser(Integer userId, UserRequestDTO userRequestDTO);
    UserResponseDTO findByUsername(String username);
    UserResponseDTO findByEmail(String email);

    // Other methods as needed (e.g., reset password, update profile)
}
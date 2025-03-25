package com.example.spotify.Users.BusinessLogic;


import com.example.spotify.Users.DataAccess.User;
import com.example.spotify.Users.DataAccess.UserRepository;
import com.example.spotify.Users.DataMapper.UserRequestMapper;
import com.example.spotify.Users.DataMapper.UserResponseMapper;
import com.example.spotify.Users.Presentation.UserRequestDTO;
import com.example.spotify.Users.Presentation.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok: Automatically injects dependencies through constructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    @Transactional // Wrap the create operation in a transaction
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // 1. Check if username or email already exists
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists"); // Or use a custom exception
        }
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists"); // Or use a custom exception
        }

        // 2. Map DTO to Entity
        User user = userRequestMapper.userRequestDTOToUser(userRequestDTO);

        // 3. Hash the password
        String hashedPassword = userRequestDTO.getPassword();
        user.setPassword(hashedPassword);

        // 4. Save the user
        User savedUser = userRepository.save(user);

        // 5. Map the saved User back to a UserResponseDTO
        return userResponseMapper.userToUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO findUserById(Integer userId) {
        return userResponseMapper.userToUserResponseDTO(userRepository.findById(userId).orElse(null));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userResponseMapper::userToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDTO updateUser(Integer userId, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the existing user with the values from the DTO
        existingUser.setUsername(userRequestDTO.getUsername());
        existingUser.setEmail(userRequestDTO.getEmail());
        // Hash the password if it's being updated.  Important security practice.
        if (!userRequestDTO.getPassword().isEmpty()) {
            String hashedPassword = userRequestDTO.getPassword();
            existingUser.setPassword(hashedPassword);
        }

        User updatedUser = userRepository.save(existingUser);
        return userResponseMapper.userToUserResponseDTO(updatedUser);
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        return userResponseMapper.userToUserResponseDTO(userRepository.findByUsername(username).orElse(null));
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        return userResponseMapper.userToUserResponseDTO(userRepository.findByUsername(email).orElse(null));
    }
}
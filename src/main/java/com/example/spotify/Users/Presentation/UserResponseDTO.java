package com.example.spotify.Users.Presentation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    // You can add other profile information here, but AVOID sending the password back!
}
package com.example.spotify.Users.DataAccess;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")  // Matches your table name
@Data // Lombok:  Generates getters, setters, toString, equals, hashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password; // Store the HASHED password.

    @Column(name = "registration_date")
    @CreationTimestamp
    private LocalDateTime registrationDate;

    // You can add other fields here, like:
    // @Column(name = "profile_picture_url")
    // private String profilePictureUrl;
    // @Column(name = "country")
    // private String country;

}
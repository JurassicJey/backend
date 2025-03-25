package com.example.spotify.Genres.DataAccess;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Genres")
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name", nullable = false, unique = true, length = 50)
    private String genreName;
}
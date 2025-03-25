package com.example.spotify.Songs.Presentation;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SongRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    private Integer artistId;

    private Integer albumId;

    private Integer genreId;

    @NotBlank(message = "Audio file path is required")
    @Size(max = 255, message = "Audio file path cannot exceed 255 characters")
    private String audioFilePath;

    @Positive(message = "Duration must be a positive number")
    private Integer duration;

    private Integer trackNumber;
}
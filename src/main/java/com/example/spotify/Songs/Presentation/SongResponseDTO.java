package com.example.spotify.Songs.Presentation;


import com.example.spotify.Albums.Controller.AlbumResponseDTO;
import com.example.spotify.Artists.Controller.ArtistResponseDTO;
import com.example.spotify.Genres.Presentation.GenreResponseDTO;
import lombok.Data;

@Data
public class SongResponseDTO {

    private Integer songId;
    private String title;
    private ArtistResponseDTO artist;
    private AlbumResponseDTO album;
    private GenreResponseDTO genre;
    private String audioFilePath;
    private Integer duration;
    private Integer trackNumber;
}
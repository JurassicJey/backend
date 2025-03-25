package com.example.spotify.Genres.BusinessLogic;

import com.example.spotify.Genres.Presentation.GenreRequestDTO;
import com.example.spotify.Genres.Presentation.GenreResponseDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    GenreResponseDTO createGenre(GenreRequestDTO genreRequestDTO);
    Optional<GenreResponseDTO> getGenreById(Integer genreId);
    List<GenreResponseDTO> getAllGenres();
    void deleteGenre(Integer genreId);
    GenreResponseDTO updateGenre(Integer genreId, GenreRequestDTO genreRequestDTO);
    Optional<GenreResponseDTO> getGenreByName(String genreName);
}
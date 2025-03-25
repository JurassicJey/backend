package com.example.spotify.Genres.DataMapper;

import com.example.spotify.Genres.DataAccess.Genre;
import com.example.spotify.Genres.Presentation.GenreResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreResponseMapper {

    GenreResponseDTO genreToGenreResponseDTO(Genre genre);
}

package com.example.spotify.Genres.DataMapper;

import com.example.spotify.Genres.DataAccess.Genre;
import com.example.spotify.Genres.Presentation.GenreRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreRequestMapper {

    Genre genreRequestDTOToGenre(GenreRequestDTO genreRequestDTO);
}

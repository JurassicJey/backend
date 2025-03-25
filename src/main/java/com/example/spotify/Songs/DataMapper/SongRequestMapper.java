package com.example.spotify.Songs.DataMapper;

import com.example.spotify.Songs.DataAccess.Song;
import com.example.spotify.Songs.Presentation.SongRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongRequestMapper {

    @Mapping(target = "artist", ignore = true)
    @Mapping(target = "album", ignore = true)
    @Mapping(target = "genre", ignore = true)
    Song songRequestDTOToSong(SongRequestDTO songRequestDTO);
}
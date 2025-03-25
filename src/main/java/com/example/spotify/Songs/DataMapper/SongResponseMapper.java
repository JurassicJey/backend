package com.example.spotify.Songs.DataMapper;


import com.example.spotify.Albums.DataMapper.AlbumResponseMapper;
import com.example.spotify.Artists.DataMapper.ArtistResponseMapper;
import com.example.spotify.Genres.DataMapper.GenreResponseMapper;
import com.example.spotify.Songs.DataAccess.Song;
import com.example.spotify.Songs.Presentation.SongResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArtistResponseMapper.class, AlbumResponseMapper.class, GenreResponseMapper.class})
public interface SongResponseMapper {

    @Mapping(source = "artist", target = "artist")
    @Mapping(source = "album", target = "album")
    @Mapping(source = "genre", target = "genre")
    SongResponseDTO songToSongResponseDTO(Song song);
}
package com.example.spotify.Songs.BusinessLogic;



import com.example.spotify.Songs.Presentation.SongRequestDTO;
import com.example.spotify.Songs.Presentation.SongResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SongService {

    SongResponseDTO createSong(SongRequestDTO songRequestDTO);
    Optional<SongResponseDTO> getSongById(Integer songId);
    List<SongResponseDTO> getAllSongs();
    void deleteSong(Integer songId);
    SongResponseDTO updateSong(Integer songId, SongRequestDTO songRequestDTO);
    List<SongResponseDTO> getSongsByArtistId(Integer artistId);
    List<SongResponseDTO> getSongsByAlbumId(Integer albumId);
    List<SongResponseDTO> getSongsByGenreId(Integer genreId);
}
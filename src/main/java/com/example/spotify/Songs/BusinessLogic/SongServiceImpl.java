package com.example.spotify.Songs.BusinessLogic;

import com.example.spotify.Albums.DataAccess.Album;
import com.example.spotify.Albums.DataAccess.AlbumRepository;
import com.example.spotify.Artists.DataAccess.Artist;
import com.example.spotify.Artists.DataAccess.ArtistRepository;

import com.example.spotify.Genres.DataAccess.Genre;
import com.example.spotify.Genres.DataAccess.GenreRepository;
import com.example.spotify.Songs.DataAccess.Song;
import com.example.spotify.Songs.DataAccess.SongRepository;
import com.example.spotify.Songs.DataMapper.SongRequestMapper;
import com.example.spotify.Songs.DataMapper.SongResponseMapper;
import com.example.spotify.Songs.Presentation.SongRequestDTO;
import com.example.spotify.Songs.Presentation.SongResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final SongRequestMapper songRequestMapper;
    private final SongResponseMapper songResponseMapper;

    @Override
    public SongResponseDTO createSong(SongRequestDTO songRequestDTO) {

        Artist artist = artistRepository.findById(songRequestDTO.getArtistId())
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + songRequestDTO.getArtistId()));


        Album album;
        if (songRequestDTO.getAlbumId() != null) {
             album = albumRepository.findById(songRequestDTO.getAlbumId())
                    .orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + songRequestDTO.getAlbumId()));

        }
        else{
            album = null;
        }

        Genre genre = genreRepository.findById(songRequestDTO.getGenreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + songRequestDTO.getGenreId()));

        System.out.println(3);

        Song song = songRequestMapper.songRequestDTOToSong(songRequestDTO);
        song.setArtist(artist);
        song.setAlbum(album);
        song.setGenre(genre);

        Song savedSong = songRepository.save(song);
        return songResponseMapper.songToSongResponseDTO(savedSong);
    }

    @Override
    public Optional<SongResponseDTO> getSongById(Integer songId) {
        return songRepository.findById(songId)
                .map(songResponseMapper::songToSongResponseDTO);
    }

    @Override
    public List<SongResponseDTO> getAllSongs() {
        return songRepository.findAll().stream()
                .map(songResponseMapper::songToSongResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSong(Integer songId) {
        if (!songRepository.existsById(songId)) {
            throw new EntityNotFoundException("Song not found with id: " + songId);
        }
        songRepository.deleteById(songId);
    }

    @Override
    public SongResponseDTO updateSong(Integer songId, SongRequestDTO songRequestDTO) {
        Song existingSong = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));

        Artist artist = artistRepository.findById(songRequestDTO.getArtistId())
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + songRequestDTO.getArtistId()));
        Album album;
        if (songRequestDTO.getAlbumId() != null) {
            album = albumRepository.findById(songRequestDTO.getAlbumId())
                    .orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + songRequestDTO.getAlbumId()));
        }
        else{
            album = null;
        }

        Genre genre = genreRepository.findById(songRequestDTO.getGenreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + songRequestDTO.getGenreId()));

        existingSong.setTitle(songRequestDTO.getTitle());
        existingSong.setArtist(artist);
        existingSong.setAlbum(album);
        existingSong.setGenre(genre);
        existingSong.setAudioFilePath(songRequestDTO.getAudioFilePath());
        existingSong.setDuration(songRequestDTO.getDuration());
        existingSong.setTrackNumber(songRequestDTO.getTrackNumber());

        Song updatedSong = songRepository.save(existingSong);
        return songResponseMapper.songToSongResponseDTO(updatedSong);
    }

    @Override
    public List<SongResponseDTO> getSongsByArtistId(Integer artistId) {
        return songRepository.findByArtist_ArtistId(artistId).stream()
                .map(songResponseMapper::songToSongResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongResponseDTO> getSongsByAlbumId(Integer albumId) {
        return songRepository.findByAlbum_AlbumId(albumId).stream()
                .map(songResponseMapper::songToSongResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongResponseDTO> getSongsByGenreId(Integer genreId) {
        return songRepository.findByGenre_GenreId(genreId).stream()
                .map(songResponseMapper::songToSongResponseDTO)
                .collect(Collectors.toList());
    }
}
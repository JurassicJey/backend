package com.example.spotify.Songs.Presentation;


import com.example.spotify.Songs.BusinessLogic.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponseDTO> createSong(@Valid @RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO createdSong = songService.createSong(songRequestDTO);
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongResponseDTO> getSongById(@PathVariable Integer songId) {
        Optional<SongResponseDTO> song = songService.getSongById(songId);
        return song.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SongResponseDTO>> getAllSongs() {
        List<SongResponseDTO> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Integer songId) {
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{songId}")
    public ResponseEntity<SongResponseDTO> updateSong(@PathVariable Integer songId, @Valid @RequestBody SongRequestDTO songRequestDTO) {
        SongResponseDTO updatedSong = songService.updateSong(songId, songRequestDTO);
        return ResponseEntity.ok(updatedSong);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<SongResponseDTO>> getSongsByArtistId(@PathVariable Integer artistId) {
        List<SongResponseDTO> songs = songService.getSongsByArtistId(artistId);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<SongResponseDTO>> getSongsByAlbumId(@PathVariable Integer albumId) {
        List<SongResponseDTO> songs = songService.getSongsByAlbumId(albumId);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<SongResponseDTO>> getSongsByGenreId(@PathVariable Integer genreId) {
        List<SongResponseDTO> songs = songService.getSongsByGenreId(genreId);
        return ResponseEntity.ok(songs);
    }
}
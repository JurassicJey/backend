package com.example.spotify.Genres.Presentation;

import com.example.spotify.Genres.BusinessLogic.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody GenreRequestDTO genreRequestDTO) {
        GenreResponseDTO createdGenre = genreService.createGenre(genreRequestDTO);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable Integer genreId) {
        Optional<GenreResponseDTO> genre = genreService.getGenreById(genreId);
        return genre.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        List<GenreResponseDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<GenreResponseDTO> updateGenre(@PathVariable Integer genreId, @Valid @RequestBody GenreRequestDTO genreRequestDTO) {
        GenreResponseDTO updatedGenre = genreService.updateGenre(genreId, genreRequestDTO);
        return ResponseEntity.ok(updatedGenre);
    }

    @GetMapping("/name/{genreName}")
    public ResponseEntity<GenreResponseDTO> getGenreByName(@PathVariable String genreName) {
        Optional<GenreResponseDTO> genre = genreService.getGenreByName(genreName);
        return genre.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
package com.example.spotify.Genres.BusinessLogic;

import com.example.spotify.Genres.DataAccess.Genre;
import com.example.spotify.Genres.DataAccess.GenreRepository;
import com.example.spotify.Genres.DataMapper.GenreRequestMapper;
import com.example.spotify.Genres.DataMapper.GenreResponseMapper;
import com.example.spotify.Genres.Presentation.GenreRequestDTO;
import com.example.spotify.Genres.Presentation.GenreResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreRequestMapper genreRequestMapper;
    private final GenreResponseMapper genreResponseMapper;

    @Override
    public GenreResponseDTO createGenre(GenreRequestDTO genreRequestDTO) {
        if (genreRepository.existsByGenreName(genreRequestDTO.getGenreName())) {
            throw new IllegalArgumentException("Genre name already exists");
        }

        Genre genre = genreRequestMapper.genreRequestDTOToGenre(genreRequestDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreResponseMapper.genreToGenreResponseDTO(savedGenre);
    }

    @Override
    public Optional<GenreResponseDTO> getGenreById(Integer genreId) {
        return genreRepository.findById(genreId)
                .map(genreResponseMapper::genreToGenreResponseDTO);
    }

    @Override
    public List<GenreResponseDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreResponseMapper::genreToGenreResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGenre(Integer genreId) {
        if (!genreRepository.existsById(genreId)) {
            throw new EntityNotFoundException("Genre not found with id: " + genreId);
        }
        genreRepository.deleteById(genreId);
    }

    @Override
    public GenreResponseDTO updateGenre(Integer genreId, GenreRequestDTO genreRequestDTO) {
        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + genreId));

        existingGenre.setGenreName(genreRequestDTO.getGenreName());

        Genre updatedGenre = genreRepository.save(existingGenre);
        return genreResponseMapper.genreToGenreResponseDTO(updatedGenre);
    }

    @Override
    public Optional<GenreResponseDTO> getGenreByName(String genreName) {
        return genreRepository.findByGenreName(genreName)
                .map(genreResponseMapper::genreToGenreResponseDTO);
    }
}
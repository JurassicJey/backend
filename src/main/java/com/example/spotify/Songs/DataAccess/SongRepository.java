package com.example.spotify.Songs.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findByTitleContaining(String title);
    List<Song> findByArtist_ArtistId(Integer artistId);
    List<Song> findByAlbum_AlbumId(Integer albumId);
    List<Song> findByGenre_GenreId(Integer genreId);

    void deleteSongsByArtist_ArtistId(Integer artistId);
}
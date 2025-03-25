package com.example.spotify.Songs.DataAccess;


import com.example.spotify.Albums.DataAccess.Album;
import com.example.spotify.Artists.DataAccess.Artist;
import com.example.spotify.Genres.DataAccess.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Songs")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "audio_file_path", nullable = false, length = 255)
    private String audioFilePath;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "track_number")
    private Integer trackNumber;

    // Add other song metadata as needed
}
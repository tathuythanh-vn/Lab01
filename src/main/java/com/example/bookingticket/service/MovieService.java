package com.example.bookingticket.service;

import com.example.bookingticket.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    private final List<Movie> movies = Arrays.asList(
        new Movie(1L, "Avengers: Endgame", "Action", 19.99, "/image/movies/avengers.jpg"),
        new Movie(2L, "The Lion King", "Animation", 15.99, "/image/movies/lionking.jpg"),
        new Movie(3L, "Joker", "Drama", 17.99, "/image/movies/joker.jpg"),
        new Movie(4L, "Spider-Man: Far From Home", "Action", 18.99, "/image/movies/spiderman.jpg"),
        new Movie(5L, "The Nun", "Horror", 14.99, "/image/movies/thenun.jpg"),
        new Movie(6L, "Vòng Tay Nắng", "Drama", 14.99, "/image/movies/vongtaynang.jpg"),
        new Movie(7L, "Misson Impossible: The Final Reckoning", "Action", 14.99, "/image/movies/thefinalreckoning.jpg"),
        new Movie(8L, "Toy Story 4", "Animation", 14.99, "/image/movies/toystory.jpg")
    );

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
} 
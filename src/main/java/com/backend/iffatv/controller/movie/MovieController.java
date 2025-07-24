package com.backend.iffatv.controller.movie;

import com.backend.iffatv.model.movie.Movie;
import com.backend.iffatv.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // ✅ Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));

    }

    // ✅ Add a new movie
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie saved = movieService.addMovie(movie);
        return ResponseEntity.ok(saved);
    }


    // ✅ Update movie (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie movie) {

        Movie updated = movieService.updateMovie(id, movie);

        return ResponseEntity.ok(updated);
    }

    // ✅ Delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/watch")
    public ResponseEntity<String> getSignedMovie(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
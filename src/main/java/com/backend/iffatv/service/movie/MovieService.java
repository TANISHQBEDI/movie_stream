package com.backend.iffatv.service.movie;

import com.backend.iffatv.exception.MovieNotFoundException;
import com.backend.iffatv.model.movie.*;
import com.backend.iffatv.repository.movie.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CastRepository castRepository;
    private final DirectorRepository directorRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie addMovie(Movie movie) {
        movie.setGenres(resolveExistingGenres(movie.getGenres()));
        movie.setCasts(resolveExistingCasts(movie.getCasts()));
        movie.setDirectors(resolveExistingDirectors(movie.getDirectors()));
        movie.setCountries(resolveExistingCountries(movie.getCountries()));
        movie.setLanguages(resolveExistingLanguages(movie.getLanguages()));

        // Save movie with managed entities attached
        return movieRepository.save(movie);
    }

    public Movie updateMovie(long id, Movie updatedMovie) {
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + id));

        if (updatedMovie.getName() != null) existing.setName(updatedMovie.getName());
        if (updatedMovie.getDescription() != null) existing.setDescription(updatedMovie.getDescription());
        if (updatedMovie.getPortraitPoster() != null) existing.setPortraitPoster(updatedMovie.getPortraitPoster());
        if (updatedMovie.getLandscapePoster() != null) existing.setLandscapePoster(updatedMovie.getLandscapePoster());
        if (updatedMovie.getYoutubeTrailer() != null) existing.setYoutubeTrailer(updatedMovie.getYoutubeTrailer());
        if (updatedMovie.getVideoPath() != null) existing.setVideoPath(updatedMovie.getVideoPath());
        if (updatedMovie.getReleaseDate() != null) existing.setReleaseDate(updatedMovie.getReleaseDate());
        if (updatedMovie.getReleaseYear() != 0) existing.setReleaseYear(updatedMovie.getReleaseYear());

        if (updatedMovie.getLikes() != null) existing.setLikes(updatedMovie.getLikes());
        if (updatedMovie.getDislikes() != null) existing.setDislikes(updatedMovie.getDislikes());
        if (updatedMovie.getViews() != null) existing.setViews(updatedMovie.getViews());

        existing.setFeatured(updatedMovie.isFeatured());

        // Resolve and set related entities if present
        if (updatedMovie.getGenres() != null)
            existing.setGenres(resolveExistingGenres(updatedMovie.getGenres()));

        if (updatedMovie.getCasts() != null)
            existing.setCasts(resolveExistingCasts(updatedMovie.getCasts()));

        if (updatedMovie.getDirectors() != null)
            existing.setDirectors(resolveExistingDirectors(updatedMovie.getDirectors()));

        if (updatedMovie.getCountries() != null)
            existing.setCountries(resolveExistingCountries(updatedMovie.getCountries()));

        if (updatedMovie.getLanguages() != null)
            existing.setLanguages(resolveExistingLanguages(updatedMovie.getLanguages()));

        return movieRepository.save(existing);
    }

    public void deleteMovie(long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }




    private List<Genre> resolveExistingGenres(List<Genre> genres) {
        if (genres == null || genres.isEmpty()) return List.of();

        return genres.stream()
                .map(g -> genreRepository.findGenreByNameIgnoreCase(g.getName())
                        .orElseGet(() -> genreRepository.save(g)))
                .toList();
    }

    private List<Cast> resolveExistingCasts(List<Cast> casts) {
        if (casts == null || casts.isEmpty()) return List.of();

        return casts.stream()
                .map(c -> castRepository.getCastByNameIgnoreCase(c.getName())
                        .orElseGet(() -> castRepository.save(c)))
                .toList();
    }

    private List<Director> resolveExistingDirectors(List<Director> directors) {
        if (directors == null || directors.isEmpty()) return List.of();

        return directors.stream()
                .map(d->directorRepository.findDirectorByNameIgnoreCase(d.getName())
                        .orElseGet(()->directorRepository.save(d))
                ).toList();
    }

    private List<Country> resolveExistingCountries(List<Country> countries) {
        if (countries == null || countries.isEmpty()) return List.of();

        return countries.stream()
                .map(c->countryRepository.findCountryByName(c.getName())
                        .orElseGet(()->countryRepository.save(c))
                ).toList();
    }

    private List<Language> resolveExistingLanguages(List<Language> languages) {
        if (languages == null || languages.isEmpty()) return List.of();

        return languages.stream()
                .map(l->languageRepository.findLanguageByNameIgnoreCase(l.getName())
                        .orElseGet(()->languageRepository.save(l))
                ).toList();
    }


}
